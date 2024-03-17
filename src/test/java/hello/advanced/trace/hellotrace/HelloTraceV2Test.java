package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

// 온전한 테스트가 아님
public class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("HelloTraceV2Test.begin_end()");
            TraceStatus status2 = trace.beginSync(status1.getTraceId(),"HelloTraceV2Test.begin_end()");
            trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello");
            TraceStatus status2 = trace.beginSync(status1.getTraceId(),"hello");
            trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}
