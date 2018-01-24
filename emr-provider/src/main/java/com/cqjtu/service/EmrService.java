/**
 * EmrService.class
 * Created in Intelij IDEA
 * <p>
 * Write Some Describe of this class here
 *
 * @author Mevur
 * @date 01/24/18 22:38
 */
package com.cqjtu.service;

import com.cqjtu.model.Emr;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EmrService")
public interface EmrService {

    Emr insert(Emr emr);

    Emr update(Emr emr);

    Emr getEmrById(String id);

    Emr delete(String id);

    List<Emr> getAll();

}
