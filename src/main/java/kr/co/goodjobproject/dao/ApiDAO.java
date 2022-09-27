package kr.co.goodjobproject.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.HireDTO;

@Mapper
@Repository
public interface ApiDAO {
	
	@Insert("INSERT INTO hire VALUES(0, #{cno}, #{bigno}, #{jno}, #{hworkinfo},"
			+ "#{hmain}, #{hreq},#{hedu}, #{hform}, #{hsal}, #{hworkday},"
			+ "#{hdate}, #{hway}, #{htitle}, #{hapi} )")
	public void insertHire(HireDTO dto);

	   @Insert("INSERT INTO company VALUES(#{cno},#{tno},#{cid},#{cpwd},#{cname},#{caddr},"
               + "#{cphone},#{cpeople},#{ceo},"
                  + "#{cmanager},#{csetup},#{chomepage},"
                     + "#{csales},#{cbin},#{cimg} )")
	   public void insertCompany(CompanyDTO dto);
}
