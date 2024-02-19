package com.statementscanner.statementscanner;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
public class ScannerService {
    public String uploadStatement(MultipartFile file){
        String text;

        try (final PDDocument document = PDDocument.load(file.getInputStream())) {
            final PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        } catch (final Exception ex) {
            log.error("Error parsing PDF", ex);
            text = "Error parsing PDF";
        }
        return text;
    }

}
