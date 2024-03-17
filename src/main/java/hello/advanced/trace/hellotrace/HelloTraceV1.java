package hello.advanced.trace.hellotrace;


import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV1 {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";
    // 로그 시작
    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId(); // UUID생성, 시작레벻
        Long startTimeMs = System.currentTimeMillis(); // 로그 시간
        // UUID,레벨로 꺼내는 PREFIX,메세지
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX,traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }
    // 로그 끝
    public void end(TraceStatus status) {
        complete(status, null);
    }
    // 로그 예외
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        // 완료 시점의 시간
        Long stopTimeMs = System.currentTimeMillis();
        // 시작 - 완료 시간 비교
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        // 현재 상태값 traceId에 담음
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms",
                    traceId.getId(),addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}",
                    traceId.getId(),addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,e.toString());
        }
    }
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append( (i == level - 1) ? "|" + prefix : "| ");
        }
        return sb.toString();
    }
}
