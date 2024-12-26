//package com.tagmaster.codetouch.util;
//
//import com.tagmaster.codetouch.entity.customer.CustomerUser;
//import com.tagmaster.codetouch.repository.customer.CustomerUserRepo;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.UUID;
//
//public class FileUtil {
//    private static final String UPLOAD_DIR = "/Users/heejunida/Desktop/FinalProject/codetouch_Company_Server/src/main/resources/static";
//    private final CustomerUserRepo customerUserRepo;
//    public FileUtil(CustomerUserRepo customerUserRepo) {
//        this.customerUserRepo = customerUserRepo;
//    }
//    public static String imageSave(MultipartFile file){
//        if(file.isEmpty()){
//            return null;
//        }
//        try {
//            File uploadDir = new File(UPLOAD_DIR + "/images");
//            if(!uploadDir.exists()){
//                uploadDir.mkdirs();
//            }
//
//            String newFileName = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//            System.out.println(newFileName);
//
//            File dest = new File(uploadDir, newFileName);
////            file.transferTo(dest);
//            try (FileOutputStream fos = new FileOutputStream(dest)) {
//                fos.write(file.getBytes());
//            }
//            String imgUrl = "http://192.168.5.10:8888/images/" + newFileName;
//            // 저장된 파일의 URL 생성
//            return imgUrl;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
//서비스에서 이미지 변경해주는 메서드
//import org.springframework.web.multipart.MultipartFile;
//
//MultipartFile file = changeInfoDTO.getUpdateImage();
//            if (file != null) {
//String originalFilename = file.getOriginalFilename();
//String splitFile = "";
//
//                if (originalFilename != null && originalFilename.contains(".")) {
//splitFile = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//        }
//
//        if (!splitFile.equalsIgnoreCase("jpg") && !splitFile.equalsIgnoreCase("png")) {
//        return "false";
//        }
//// 확장자 검증 후 저장 수행
//String image = FileUtil.imageSave(file);
//                companyUser.setImageUrl(image);