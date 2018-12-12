package com.claymore.bamder.cybersecurity.event.support;

import com.claymore.bamder.cybersecurity.event.entity.EventsInfoEntity;
import com.claymore.bamder.cybersecurity.event.enums.ChuzhiStatus;
import com.claymore.bamder.cybersecurity.event.enums.EventLevel;
import com.claymore.bamder.cybersecurity.event.enums.EventType;
import com.claymore.bamder.cybersecurity.gj.domain.event.response.Event;

/**
 * @author li.zhuo
 * @create 2018/11/28 9:33 PM
 * @since 1.0.0
 */
public class EventSupport {

    /*public static EventResponse transfer2EventResponse(List<EventsInfoEntity> eventsInfo) {
        EventResponse response = new EventResponse();
        Long eventType1 = eventsInfo.stream().filter(eventsInfoEntity -> eventsInfoEntity.getEventType() == 1).collect(Collectors.counting());
        Long eventType2 = eventsInfo.size() - eventType1;

        Long eventLevel1 = eventsInfo.stream().filter(eventsInfoEntity -> eventsInfoEntity.getEventLevel() == 1).collect(Collectors.counting());
        Long eventLevel2 = eventsInfo.stream().filter(eventsInfoEntity -> eventsInfoEntity.getEventLevel() == 2).collect(Collectors.counting());
        Long eventLevel3 = eventsInfo.size() - eventLevel1 - eventLevel2;

        Long zcToEventTotal = eventsInfo.stream().map(eventsInfoEntity -> eventsInfoEntity.getTxzd0() + eventsInfoEntity.getMbsj0() + eventsInfoEntity.getGjqm0() + eventsInfoEntity.getWgxw0()).reduce(Long::sum).orElse(0L);
        Long ycToEventTotal = eventsInfo.stream().map(eventsInfoEntity -> eventsInfoEntity.getTxzd1() + eventsInfoEntity.getMbsj1() + eventsInfoEntity.getGjqm1() + eventsInfoEntity.getWgxw1()).reduce(Long::sum).orElse(0L);
        Long gjToEventTotal = zcToEventTotal + ycToEventTotal;

        //TODO  事件类型转换需要再看

        response.setEventType1(eventType1);
        response.setEventType2(eventType2);

        response.setEventLevel1(eventLevel1);
        response.setEventLevel2(eventLevel2);
        response.setEventLevel3(eventLevel3);



        response.setEvents(transfer2EventList(eventsInfo));
        return response;

    }

    public static List<Event> transfer2EventList(List<EventsInfoEntity> eventsInfo) {
        return eventsInfo.stream().map(EventSupport::transfer2Event).collect(Collectors.toList());
    }*/

    public static Event transfer2Event(EventsInfoEntity entity) {
        Event event = new Event();
        event.setChuzhiStatus(ChuzhiStatus.getByValue(entity.getChuzhiStatus()).getDesc());
        event.setEventLevel(EventLevel.getByValue(entity.getEventLevel()).getDesc());
        event.setEventType(EventType.getByValue(entity.getEventType()).getDesc());
        event.setContent(entity.getContent());
        event.setEventTime(entity.getEventTime());
        return event;
    }


}
