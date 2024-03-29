package com.medilabosolutions.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.medilabosolutions.model.Note;
import com.medilabosolutions.model.SumTermTriggers;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface NoteRepository extends ReactiveMongoRepository<Note, String> {

    @Query("{'patient.id': ?0}")
    Flux<Note> findByPatientId(Long patientId);

    @Query(value = "{'patient.id': ?0}", sort = "{'date':1}")
    Flux<Note> findByPatientId(Long patientId, Pageable pageable);

    @Query(value = "{'patient.id': ?0}", delete = true)
    Flux<Note> deleteNoteByPatientId(Long patientId);

    Mono<Long> countByPatientId(Long patientId);

    // @Aggregation(pipeline = {
    // "{'$match': {'patient._id': ?0}}",
    // "{$addFields:{countTriggers:{$size:{$regexFindAll:{input:'$content',regex: ?1 ,options:'i'}}}}}",
    // "{$group:{_id:'$patient._id',sumTermTriggers:{$sum:'$countTriggers'}}}"
    // })
    // Mono<SumTermTriggers> countTriggersIntoPatientNotes(Long patientId, String triggers);

    @Aggregation(pipeline = {
            "{$match: {'patient._id': ?0}}",
            "{$addFields: {termsTrigger: {$regexFindAll: {input: '$content',regex:?1,options: 'i'}}}}",
            "{$set: { termsTrigger: { $map: { input: '$termsTrigger.match', in: { $toLower: '$$this'}}}}}",
            "{$unwind: {path: '$termsTrigger', includeArrayIndex: 'string',preserveNullAndEmptyArrays: true}}",
            "{$group: { _id: '$patient._id', termsTrigger: { $addToSet: '$termsTrigger'}}}",
            "{ $project: { sumTermTriggers: { $size: '$termsTrigger'}}}}"})
    Mono<SumTermTriggers> countTriggersIntoPatientNotes(Long patientId, String triggers);
}
