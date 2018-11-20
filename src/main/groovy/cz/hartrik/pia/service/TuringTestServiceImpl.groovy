package cz.hartrik.pia.service

import org.springframework.stereotype.Service

import java.util.concurrent.ConcurrentHashMap

/**
 *
 * @version 2018-11-20
 * @author Patrik Harag
 */
@Service
class TuringTestServiceImpl implements TuringTestService {

    private final Map<String, TuringTest> tests = new ConcurrentHashMap<>()

    @Override
    void register(TuringTest test) {
        tests.put(test.id, test)
    }

    @Override
    TuringTest randomTest() {
        def list = tests.values().toList()
        Collections.shuffle(list)
        return list.first()
    }

    @Override
    TuringTest findTest(String id) {
        return tests.get(id)
    }
}
