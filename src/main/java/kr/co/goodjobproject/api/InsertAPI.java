package kr.co.goodjobproject.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.goodjobproject.dao.ApiDAO;
import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.HireDTO;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class InsertAPI {
	
	HashMap<String, HireDTO> nameMap = new HashMap<String, HireDTO>(); // 중복 제거를 위한 HashMap

	// 동작 방식
	// 1. inserHire 는 HashMap을 return 하며, 페이지번호, 가져올 데이터 갯수, cno 번호
	// 2. 사람인 API 를 사용해서 정보를 가져옴 -> 가져온 json 를 HireDTO 에 맞게 파싱
	// 3. 파싱된 데이터를 hdto 에 담은 후 중복된 회사명을 제거하기 위해 HashMap 을 사용해서 map(회사명, hdto) 
	// ==> 중복된 회사명을 제거하는 이유는 채용정보 hire 에 따른 cno 를 맞추기 위해서이다. 만약 회사명 중복제거가 없으면 똑같은 회사명이라도 hire 에 들어가는 cno 가 달라지게 된다.
	// 4. insertCompa 는 CompanyDTO 를 return 하며, 공공데이터포탈에 있는 API를 사용해서 회사명에 따른 회사 정보를 가져옴
	// 5. 회사명으로 데이터를 가져올 수 없는 경우 json 을 파싱했을 때 데이터가 없고, 따라서 isEmpty 했을 때 true 가 됨으로 이때는 메서드 종료.
	//    데이터가 있는 경우 CompanyDTO 에 파싱한 데이터를 담음
	// 6. 채용정보를 가져온 후 -> 해당 채용 정보에서 회사명만 빼내서 회사 정보 검색 -> hireDTO 와 CompanyDTO 를 기준으로 테이블에 정보 insert
	public HashMap<String, HireDTO> insertHire(int start, int end, int Rcnt) {

		try {
			String text = URLEncoder.encode("", "UTF-8");
			
			String apiURL = APIProperties.getHireapi() +start+"&count="+end;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");

			int responseCode = con.getResponseCode();
			BufferedReader br;

			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
//	            System.out.println(response.toString());

			String result = response.toString();
			JSONParser json = new JSONParser();
			JSONObject resultJSON = (JSONObject) json.parse(result);

//	            System.out.println("파싱 결과 : " + resultJSON);
//	            System.out.println("파싱 결과2 : " + resultJSON.get("jobs"));

			JSONObject jobs = (JSONObject) resultJSON.get("jobs");
			JSONArray jobArr = (JSONArray) jobs.get("job");
//	            System.out.println("arr : " + job);
			
			// cno 값
	        int cnt = Rcnt;

			for (Object job : jobArr) {
				ArrayList<String> list = new ArrayList<String>();
				HireDTO hdto = new HireDTO();
				
				JSONObject jsonObj = (JSONObject) job;
				JSONObject company = (JSONObject) jsonObj.get("company");
				
				JSONObject position = (JSONObject) jsonObj.get("position");
				
				JSONObject mainJob = (JSONObject) company.get("detail");
				
				JSONObject expLev = (JSONObject) position.get("experience-level");
				
				JSONObject reqEdu = (JSONObject) position.get("required-education-level");
				
				JSONObject job_type = (JSONObject) position.get("job-type");
				
				JSONObject salary = (JSONObject)jsonObj.get("salary");
				
//				JSONObject hsal = (JSONObject) salary.get("name");
//				System.out.println("salary : "+salary);
//				System.out.println("sal : "+salary.get("name"));
				
				JSONObject expir = (JSONObject) job;
				
				String date = expir.get("expiration-date").toString().split("T")[0];
				String time = expir.get("expiration-date").toString().split("T")[1].split("\\+")[0];

				JSONObject jobcode = (JSONObject) position.get("job-mid-code");
//				System.out.println("jobcode"+ " "+jobcode.get("code").toString().split(",")[0]);
				
				String cname = mainJob.get("name").toString(); // 회사명
				
				// location
				JSONObject location = (JSONObject) position.get("location");
				StringTokenizer st = new StringTokenizer((String)location.get("code"), ",");
				int locationCode = Integer.parseInt(st.nextToken());
				
				
				hdto.setCno(++cnt);
				hdto.setBigno(locationCode);
				hdto.setJno(Integer.parseInt(jobcode.get("code").toString().split(",")[0]));
				hdto.setHworkinfo(expLev.get("name").toString());
				hdto.setHmain(jsonObj.get("url").toString());
				hdto.setHreq("-");
				hdto.setHedu(reqEdu.get("name").toString());
				hdto.setHsal(salary.get("name").toString());
				hdto.setHworkday("-");
				hdto.setHdate(date.toString()+" "+time.toString());
				hdto.setHway("-");
				hdto.setHtitle(position.get("title").toString());
				hdto.setHapi("Powered by <a href=\"http://www.saramin.co.kr\" target=\"_blank\">취업 사람인</a>");
				
//				list.add(expLev.get("name").toString()); 
//				list.add("해당 채용 공고는 사람인에서 가져온 정보입니다. 보다 자세한 정보는 아래 내용을 참고 부탁드립니다 \n" + mainJob.get("href").toString());
//				list.add("-");
//				list.add(reqEdu.get("name").toString());
//				
				if(job_type.get("name") != null) {
//					list.add(job_type.get("name").toString());
					hdto.setHform(job_type.get("name").toString());
				}else {
					hdto.setHform("-");
//					list.add("-");
				}
////				list.add(job_type.get("name").toString());
//				list.add(hsal.get("name").toString());
//				
//				list.add("-");
//				list.add(date+" "+time);
//				list.add("-");
//				list.add(position.get("title").toString());
//				list.add(jobcode.get("code").toString());
//				list.add("Powered by <a href=\"http://www.saramin.co.kr\" target=\"_blank\">취업 사람인</a>");
				
				// 중복제거를 위한 map
				nameMap.put(cname, hdto);
				System.out.println(nameMap.get(cname));
				System.out.println(hdto);
				
//				dao.insertHire(hdto);
//				System.out.println("cname : "+cname);
//				insertCompa(cname, cnt, dao);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return nameMap;
	}
	
	public CompanyDTO insertCompa(String cname, int cnt) throws Exception {
			System.out.println("insertCompa : "+cnt);
		
	       StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1160100/service/GetCorpBasicInfoService/getCorpOutline"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + APIProperties.getCompanyservicekey()); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("4", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*결과형식(xml/json)*/
	        urlBuilder.append("&" + URLEncoder.encode("basDt","UTF-8") + "=" + URLEncoder.encode("20200509", "UTF-8")); /*기준일자*/
	        urlBuilder.append("&" + URLEncoder.encode("corpNm","UTF-8") + "=" + URLEncoder.encode(cname, "UTF-8")); /*법인(法人)의 명칭*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	       // System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        String str = sb.toString();
	        
	        // json parser
	        JSONParser jp = new JSONParser();
	        JSONObject job = (JSONObject)jp.parse(str);
	        JSONObject job2 = (JSONObject)job.get("response");
	        JSONObject job3 = (JSONObject)job2.get("body");
	        JSONObject job4 =  (JSONObject)job3.get("items");
	        JSONArray job5 =  (JSONArray)job4.get("item");
	        
	        if(job5.isEmpty()) {
	        	return null;
	        }else {
	        	JSONObject test1 = (JSONObject) job5.get(0);
	        	
		        int tno = 2;
		        String cid ="company"+ cnt;
		        String cpwd = "test1234";
		        String name = (String) test1.get("corpNm");
		        String caddr = (String) test1.get("enpBsadr");
		        String cphone = (String) test1.get("enpTlno");
		        int cpeople = Integer.parseInt(test1.get("enpEmpeCnt").toString());
		        String ceo = (String)test1.get("enpRprFnm");
		        String cmanager = "담당자";
		        String csetup = test1.get("enpEstbDt").toString();
		        String chomepage = test1.get("enpHmpgUrl").toString();
		        String csales = String.valueOf(Integer.parseInt(test1.get("enpEmpeCnt").toString())*Integer.parseInt(test1.get("enpPn1AvgSlryAmt").toString()));
		        String cbin = test1.get("bzno").toString();
		        String cimg = "";
		        
				CompanyDTO dto = new CompanyDTO(cnt, tno, cid, cpwd, name, caddr, cphone, cpeople, ceo, cmanager, csetup, chomepage, csales, cbin, "noEmail", cimg, "ROLE_USER", "true");

//				dao.insertCompany(dto);
		        
//		        System.out.println(test1);
				
				return dto;
	        }

		
	}

}
