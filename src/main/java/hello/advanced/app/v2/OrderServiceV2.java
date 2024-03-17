package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepositoryV0;
    private final HelloTraceV2 helloTraceV2;
    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = helloTraceV2.beginSync(traceId,"OrderServiceV2.orderItem()");
            orderRepositoryV0.save(status.getTraceId(),itemId);
            helloTraceV2.end(status);
        } catch (Exception e) {
            helloTraceV2.exception(status,e);
            throw e;
        }
    }
}
