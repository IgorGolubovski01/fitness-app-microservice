package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService aiService;
    private final RecommendationRepository recommendationRepository;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void processActivity(Activity activity) {
        try {


            log.info("Receiving activity for processing: {}", activity != null ? activity.getId() : "null");
            Recommendation recommendation = aiService.generateRecommendation(activity);
            recommendationRepository.save(recommendation);
//            log.info("Generated recommendation: {}", aiService.generateRecommendation(activity));


        } catch (Exception ex) {
            log.error("Failed to process activity message: {}", activity, ex);
            throw ex;
        }

    }
}