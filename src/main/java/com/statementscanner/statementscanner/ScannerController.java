package com.statementscanner.statementscanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ScannerController {

    @Autowired
    ScannerService scannerService;

    @PostMapping(value= "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> uploadStatement (@RequestParam("file") final MultipartFile pdfFile){
        String message = "";
        try {
            message = "Uploaded the file successfully: " + pdfFile.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.builder().response(this.scannerService.uploadStatement(pdfFile)).build());
        } catch (Exception e) {
            message = "Could not upload the file: " + pdfFile.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

}
