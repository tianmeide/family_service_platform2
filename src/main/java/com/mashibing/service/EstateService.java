package com.mashibing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.bean.FcBuilding;
import com.mashibing.bean.FcEstate;
import com.mashibing.bean.TblCompany;
import com.mashibing.mapper.common.FcBuildingMapper;
import com.mashibing.mapper.common.FcEstateMapper;
import com.mashibing.mapper.common.TblCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstateService {

    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private FcEstateMapper fcEstateMapper;
    @Autowired
    private FcBuildingMapper fcBuildingMapper;

    public List<TblCompany> selectCompany(){

        List<TblCompany> companyNames = tblCompanyMapper.selectCompany();
        return companyNames;
    }

    /**
     * 再插入数据之前，最好对当前信息做判断，判断住宅编码是否存在，如果存在则不允许插入，如果不存在才允许插入
     * @param fcEstate
     * @return
     */
    
    public Integer insertEstate(FcEstate fcEstate){
        //定义查询包装类
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("estate_code",fcEstate.getEstateCode());
        FcEstate findResult = fcEstateMapper.selectOne(queryWrapper);
        //定义返回的结果
        int result = 0;
        if(findResult != null){
            return result;
        }else {
            result = fcEstateMapper.insert(fcEstate);
            return result;
        }
    }

    /**
     * 先插入数据，再查询数据
     * @param buildingNumber
     * @param estateCode
     * @return
     */
    public List<FcBuilding> selectBuilding(Integer buildingNumber,String estateCode){
        List<FcBuilding> fcBuildings = new ArrayList<>();
        for(int i= 0;i<buildingNumber;i++){
            FcBuilding fcBuilding = new FcBuilding();
            fcBuilding.setBuildingCode("B" +(i+1));
            fcBuilding.setBuildingName("第" + (i+1)+ "号楼");
            fcBuilding.setEstateCode(estateCode);
            fcBuildingMapper.insert(fcBuilding);
            fcBuildings.add(fcBuilding);
        }
        return fcBuildings;
    }
}
