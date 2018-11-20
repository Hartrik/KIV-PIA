package cz.hartrik.pia.service

import org.springframework.stereotype.Service

/**
 *
 * @version 2018-11-20
 * @author Patrik Harag
 */
@Service
interface TuringTestService {

    void register(TuringTest test)

    TuringTest randomTest()

    TuringTest findTest(String id)

}
