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

import com.cqjtu.mapperexp.EmrMapperExp;
import com.cqjtu.model.Emr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmrServiceImpl implements EmrService, Pageable {

    @Autowired
    EmrMapperExp mapperExp;

    @Override
    public Emr insert(Emr emr) {
        if (mapperExp.insert(emr) > 0) {
            return emr;
        } else {
            return null;
        }
    }

    @Override
    public Emr update(Emr emr) {
        if (mapperExp.update(emr) > 0) {
            return emr;
        } else {
            return null;
        }
    }

    @Override
    public Emr getEmrById(String id) {
        return mapperExp.selectById(id);
    }

    @Override
    public Emr delete(String id) {
        Emr emr = mapperExp.selectById(id);
        if (null == emr) {
            //emr don't exist
            return null;
        } else  {
            if (mapperExp.delete(id) == 1) {
                return emr;
            } else {
                //delete failed
                return null;
            }
        }
    }

    @Override
    public List<Emr> getAll() {
        return mapperExp.getEmr(null, null);
    }

    @Override
    public Integer count(Map<String, Object> param) {
        return mapperExp.count(param);
    }

    @Override
    public List<Emr> emrs(Integer page, Integer limit) {
        return mapperExp.getEmr(page, limit);
    }
}
