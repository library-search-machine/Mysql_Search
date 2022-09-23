package com.example.demo;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.apache.tomcat.jni.Library;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(BookRepository bookRepository) {
//
//        return (args) -> {
//            long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
//         /*   int total=-1,size=500,page=1;
//            Long resultInt=0l;
//            do {
//                String result = "";
//                URL url = new URL("http://data4library.kr/api/itemSrch?authKey=d39c7f3d9d7c547994399f4d90e13c5529b752ba9f9345f4cf8a884c2e7e63fa&libCode=127058&type=ALL&pageNo="+page+"&pageSize="+size+"&format=json");
//                BufferedReader bf;
//                bf = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
//                result = bf.readLine();
//                JSONParser jsonParser = new JSONParser();
//                JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
//                JSONObject response = (JSONObject) jsonObject.get("response");
//                resultInt=(Long) response.get("numFound");
//                JSONArray infoArr = (JSONArray) response.get("docs");
//                List<library> libraries = new ArrayList<>();
//                for (int i = 0; i < infoArr.size(); i++) {
//                    JSONObject tmp = (JSONObject) infoArr.get(i);
//                    JSONObject tmp2 = (JSONObject) tmp.get("doc");
//                    libraries.add(new library((String) tmp2.get("bookname"), (String) response.get("libNm")));
//                }
//                libraryRepository.saveAll(libraries);
//                page++;
//            }while(resultInt>total);
//*/
//
//            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("C:\\Users\\tlseh\\Downloads\\도서관"))) {
//                for (Path file: stream) {
//                    List<List<String>> csvList = new ArrayList<>();
//                    List<Book> books = new ArrayList<>();
//
//                    String[] tmp = file.getFileName().toString().split("\\.");
//                    BufferedReader br = null;
//                    String line = "";
//
//                    FileInputStream input=new FileInputStream("C:\\Users\\tlseh\\Downloads\\도서관\\"+file.getFileName());
//                    InputStreamReader reader=new InputStreamReader(input,"UTF-8");
//                    try {
//                        br = new BufferedReader(reader);
//
//                        while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
//                            List<String> aLine;
//                            String[] lineArr = line.split("\\|"); // 파일의 한 줄을 |로 나누어 배열에 저장 후 리스트로 변환한다.
//                            if(lineArr.length==0) continue;
//
//                            //한줄 밀리는 현상에 대해서 픽스하기위한 코드
//                            if(lineArr.length!=13){
//                                StringBuilder sb = new StringBuilder();
//                                sb.append(line);
//                                line = br.readLine();
//                                sb.append(line);
//                                lineArr = sb.toString().split("\\|");
//                            }
//
//                            aLine = Arrays.asList(lineArr);
//
//                            books.add(new Book(aLine.get(1),tmp[0],aLine.get(3),aLine.get(4),aLine.get(2),aLine.get(9),aLine.get(5),aLine.get(8)));
//                            csvList.add(aLine);
//
//                        }
//                        bookRepository.saveAll(books);
//                        input.close();
//                        reader.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        try {
//                            if (br != null) {
//                                br.close(); // 사용 후 BufferedReader를 닫아준다.
//
//                            }
//                        } catch(IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//
//                }
//            } catch (IOException | DirectoryIteratorException ex) {
//                System.err.println(ex);
//            }
//
//
//            long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
//            long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
//            System.out.println("시간차이(m) : "+secDiffTime);
//
//        };
//    }




}
