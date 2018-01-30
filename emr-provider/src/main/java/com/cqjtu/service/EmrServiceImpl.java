/**
 * EmrServiceImpl.class
 * Created in Intelij IDEA
 * <p>
 * Write Some Describe of this class here
 *
 * @author Mevur
 * @date 01/24/18 22:46
 */
package com.cqjtu.service;

import com.cqjtu.mapper.EmrMapper;
import com.cqjtu.mapperexp.EmrMapperExp;
import com.cqjtu.model.Emr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmrServiceImpl implements EmrService, Pageable {

    @Autowired
    EmrMapper mapper;

    @Autowired
    EmrMapperExp emrMapperExp;

    @Override
    public Emr insert(Emr emr) {
        if (mapper.insert(emr) > 0) {
            return emr;
        } else {
            return null;
        }
    }

    @Override
    public Emr update(Emr emr) {
        if (mapper.updateByPrimaryKeySelective(emr) > 0) {
            return emr;
        } else {
            return null;
        }
    }

    @Override
    public Emr getEmrById(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Emr delete(String id) {
        Emr emr = mapper.selectByPrimaryKey(id);
        if (null == emr) {
            //emr don't exist
            return null;
        } else  {
            if (mapper.deleteByPrimaryKey(id) == 1) {
                return emr;
            } else {
                //delete failed
                return null;
            }
        }
    }

    @Override
    public List<Emr> getAll() {
        return mapper.selectByExample(null);
    }

    @Override
    public Integer count() {
        return emrMapperExp.count();
    }

    @Override
    public List<Emr> emrs(Integer page, Integer limit) {
        return emrMapperExp.getEmr(page, limit);
    }
}
