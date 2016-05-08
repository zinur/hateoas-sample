package com.sample.hateoas;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sample.hateoas.domain.Child;
import com.sample.hateoas.domain.GardenGroup;
import com.sample.hateoas.domain.KinderGartener;
import com.sample.hateoas.repository.ChildRepository;
import com.sample.hateoas.repository.GardenGroupRepository;
import com.sample.hateoas.repository.KinderGartenerRepository;

@Configuration
public class ApplicationConfiguration {

    private static final Integer[] MOBILE_OPERATOR_CODES = new Integer[] { 39, 50, 63, 66, 67, 68, 93, 95, 96, 97, 98,
            99 };

    private static final String[] DISTRICTS = new String[] { "Голосеево", "Дарница", "Деснянский", "Днепровский",
            "Оболонь", "Печерск", "Подол", "Святошино", "Соломенский", "Шевченковский" };

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private GardenGroupRepository gardenGroupRepository;

    @Autowired
    private KinderGartenerRepository kinderGartenerRepository;

    @Bean
    CommandLineRunner commandLineRunner(ApplicationConfiguration dataLoader) {
        return (o) -> dataLoader.load();
    }

    @Bean
    public Module newJavaTimeModule() {
        return new JavaTimeModule();
    }

    protected void load() {
        int amount = 100;

        for (int i = 0; i < amount; ++i) {
            Child child = nextChild();
            childRepository.save(child);
        }

        for (int i = 0; i < 10; i++) {
            KinderGartener kinderGartener = nextKinderGartener();
            kinderGartenerRepository.save(kinderGartener);
        }

        for (int i = 0; i < 10; i++) {
            GardenGroup gardenGroup = nextGardenGroup(i);
            gardenGroupRepository.save(gardenGroup);
        }

    }

    private GardenGroup nextGardenGroup(int page) {

        final Page<Child> children = childRepository.findAll(new PageRequest(page, 10));
        final Page<KinderGartener> kinderGarteners = kinderGartenerRepository.findAll(new PageRequest(page, 1));
        GardenGroup gardenGroup = new GardenGroup();
        gardenGroup.setChildren(children.getContent());
        gardenGroup.setKinderGartener(kinderGarteners.getContent().get(0));
        List<GardenGroup.Type> typeList = Arrays.stream(GardenGroup.Type.values()).collect(Collectors.toList());

        gardenGroup.setType(typeList.get(nextInt((typeList.size()))));
        return gardenGroup;

    }

    private static Child nextChild() {
        Child child = new Child();
        child.setPhoneNumber(nextPhoneNumber());
        child.setLocation(new Child.Location("Киев", "Соломенка"));
        return child;
    }

    private static KinderGartener nextKinderGartener() {
        KinderGartener kinderGartener = new KinderGartener();
        kinderGartener.setPhoneNumber(nextPhoneNumber());
        return kinderGartener;
    }

    private static String nextPhoneNumber() {
        return String.format("0%d%07d", nextMobileOperatorCode(), nextInt(10000000));
    }

    private static int nextMobileOperatorCode() {
        return nextRandomFromArray(MOBILE_OPERATOR_CODES);
    }

    private static <T> T nextRandomFromArray(T[] array) {
        return array[nextInt(array.length)];
    }

    private static int nextInt(int bound) {
        return new Random().nextInt(bound);
    }

}
