package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

// 온전한 테스트가 아님
public class HelloTraceV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        // begin() 매소드 시작과 동시에 UUID,레벨 생성 -> 생성된 값이 하나의 STATUS에 담김
        TraceStatus status = trace.begin("hello");
        // end() 매소드 시작
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}
