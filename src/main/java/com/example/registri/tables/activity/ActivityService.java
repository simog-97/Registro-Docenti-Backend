package com.example.registri.tables.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    public Activity addActivity(Activity e) {
        return repository.save(e);
    }

    public Optional<Activity> getActivity(Long id) {
        return repository.findById(id);
    }

    public List<Activity> getAllActivitys() {
        List<Activity> output = new ArrayList<Activity>();
        repository.findAll().forEach(output::add);
        return output;
    }

    public Activity updateActivity(Activity e) {
        return repository.save(e);
    }

    public void deleteActivity(Activity e) {
        repository.delete(e);
}

    public void deleteActivity(Long id) {
        repository.deleteById(id);
    }

    public List<Activity> getByRegisterId(Long id) {
        return repository.findByRegisterId(id);
    }
}