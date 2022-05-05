package com.emergys.akagibackend.controller;

import com.emergys.akagibackend.mail.MailEmployee;
import com.emergys.akagibackend.model.*;
import com.emergys.akagibackend.repository.UsersRepository;
import com.emergys.akagibackend.service.MailService;
import com.emergys.akagibackend.repository.CompanyRepository;
import com.emergys.akagibackend.repository.EmployeeRepository;
import com.emergys.akagibackend.service.FileStorageService;
import com.emergys.akagibackend.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class AkagiController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/accessDashboard")
    public String getAccess(@RequestBody AccessWraper accessObj) {
        Users user = usersRepository.findByEmail(accessObj.getEmail());
        System.out.println("Que mande? " + accessObj.toString());
        System.out.println("Que tenemos? " + user.toString());
        System.out.println("Son iguales? " + user.getPsswrd().equals(accessObj.getCode()));
        if(user.getPsswrd().equals(accessObj.getCode())) {
            return "KR4yqHq6FQ" + user.getId() + "6sWFjWx3Ud";
        } else {
            return null;
        }
    }

    //get Employees
    @GetMapping("/dashboard")
    public DashboardVO getEmployeesByCompany(@RequestParam String code) {
        Integer id = 0;
        List<Employee> fullEmployeeList = new ArrayList<>();
        if( (code.contains("KR4yqHq6FQ")) && (code.contains("6sWFjWx3Ud")) ) {
            id = Integer.parseInt(String.valueOf(code.charAt(10)));
        }
        Optional<Users> user = usersRepository.findById(id);
        if(user.isPresent()) {
            Set<Company> companies = user.get().getCompanies();
            Iterator<Company> companyIterator = companies.iterator();
            if(companies.size() == 10) {
                fullEmployeeList = employeeRepository.findAll();
            } else {
                while(companyIterator.hasNext()) {
                    fullEmployeeList.addAll(employeeRepository.findByCompany(companyIterator.next()));
                }
            }
            DashboardVO result = new DashboardVO(user.get(), fullEmployeeList);
            return result;
        } else {
            return null;
        }
    }

    @GetMapping("/getRecord")
    public RecordInfoVO getEmployeeByLink(@RequestParam String code) {
        RecordInfoVO result = new RecordInfoVO(employeeRepository.findByInviteLink(code));
        return result;
    }

    //Update Employee
    @PostMapping("/storeFile")
    public Integer storeFile(@RequestParam MultipartFile file) {
        try {
            String fileName = file.getName().toUpperCase();
            boolean extension = fileName.endsWith(".JPG") || fileName.endsWith(".JPEG") || fileName.endsWith(".PNG")
                    || fileName.endsWith(".PDF") || fileName.endsWith(".DOC") || fileName.endsWith(".DOCX");
            if (extension) {
                FileStorage dbFile = storageService.store(file);
                System.out.println("Uploaded the file successfully: " + file.getOriginalFilename() + "!");
                return dbFile.getId();
            } else {
                return 0;
            }

        } catch (Exception e) {
                System.out.println("Could not upload the file: " + file.getOriginalFilename() + "!");
                return 0;
        }
    }

    @PostMapping("/updateRecord")
    public Integer updateEmployeeRecord(@RequestBody UpdateRecordVO toUpdate) {
        System.out.println("UpdateRecord Request" + toUpdate);
        Optional<Employee> empExists = employeeRepository.findById(toUpdate.getIdEmployee());
        if(empExists.isPresent() ) {
            Employee empUpdate = empExists.get();
            empUpdate.setVaccineStatus(toUpdate.getStatus());
            empUpdate.currentTimeStamp();
            if(empUpdate.getVaccineStatus() == 1) {
                FileStorage dbFile = storageService.getFile(toUpdate.getIdFile());
                String fileDownloadUri = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/api/v1/files/")
                        .path(dbFile.getId().toString())
                        .toUriString();
                empUpdate.setIdFile(dbFile.getId());
                empUpdate.setCardLink(fileDownloadUri);
                employeeRepository.save(empUpdate);
                mailService.sendYesMail(new MailEmployee(empUpdate));
            } else {
                employeeRepository.save(empUpdate);
                mailService.sendNoMail(new MailEmployee(empUpdate));
            }
            return empUpdate.getId();
        } else {
            return 0;
        }
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileStorage fileDB = storageService.getFile(Integer.parseInt(id));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping("/sendReminder")
    public ResponseEntity<ResponseMessage> sendInvites(@RequestParam Integer idUser) {
        Integer result = 0;
        List<Employee> fullEmployeeList = new ArrayList<>();
        Optional<Users> user = usersRepository.findById(idUser);
        if(user.isPresent()) {
            Set<Company> companies = user.get().getCompanies();
            Iterator<Company> companyIterator = companies.iterator();
            if(companies.size() == 10) {
                fullEmployeeList = employeeRepository.findAll();
            } else {
                while(companyIterator.hasNext()) {
                    fullEmployeeList.addAll(employeeRepository.findByCompany(companyIterator.next()));
                }
            }
            fullEmployeeList = filterByDone(fullEmployeeList);
            if(!fullEmployeeList.isEmpty()) {
                for(int i=0; i < fullEmployeeList.size(); i++) {
                    result = result + mailService.sendInvitation(new MailEmployee(fullEmployeeList.get(i)));
                }
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(result + " messages successfully sent"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No matching results found"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No matching results found"));
        }

    }

    @GetMapping("/individualReminder")
    public ResponseEntity<ResponseMessage> sendIndividualInvite(@RequestParam String email) {
        Integer flag = 0;
        List<Employee> empExists = employeeRepository.findByEmail(email);
//        System.out.println(empExists);
        if(empExists.size() > 0) {
//            for(int i = 0; i < empExists.size(); i++) {
//                flag = mailService.sendInvitation(new MailEmployee(empExists.get(i)));
//            }
            flag = mailService.sendInvitation(new MailEmployee(empExists.get(0)));
            if(flag > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Message successfully sent"));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Message service failure"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No matching results found"));
        }
    }

    public List<Employee> filterByDone(List<Employee> entryList) {
        List<Employee> result = new ArrayList<>();
        entryList.forEach(employee -> {
            if(employee.getVaccineStatus() != 1) {
                result.add(employee);
            }
        });
        return result;
    }
}
