package com.mashibing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.bean.FcBuilding;
import com.mashibing.bean.FcEstate;
import com.mashibing.bean.FcUnit;
import com.mashibing.bean.TblCompany;
import com.mashibing.mapper.common.FcBuildingMapper;
import com.mashibing.mapper.common.FcEstateMapper;
import com.mashibing.mapper.common.FcUnitMapper;
import com.mashibing.mapper.common.TblCompanyMapper;
import com.mashibing.vo.UnitMessage;
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
    @Autowired
    private FcUnitMapper fcUnitMapper;

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

    /**
     * 完成楼宇更新的功能
     * @param fcBuilding
     * @return
     */
    public Integer updateBuilding(FcBuilding fcBuilding){
        int result = fcBuildingMapper.updateById(fcBuilding);
        return result;
    }

    public List<FcUnit> selectUnit(UnitMessage unitMessage){
        //定义返回值集合
        List<FcUnit> fcUnits = new ArrayList<>();
        for(int i = 0; i<unitMessage.getUnitCount();i++){
            FcUnit fcUnit = new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode("U" + (i+1));
            fcUnit.setUnitName("第" + (i+1) + "单元");
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        return fcUnits;
    }

    public Integer updateUnit(FcUnit fcUnit){
        int i = fcUnitMapper.updateById(fcUnit);
        return i;
    }
}
