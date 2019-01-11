package com.example.axel.bucketlist.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.axel.bucketlist.Database.Entity.BucketListItem;
import com.example.axel.bucketlist.Repository.BucketListItemRepository;

import java.util.List;

public class BucketListItemViewModel extends AndroidViewModel {
    private BucketListItemRepository repository;
    private LiveData<List<BucketListItem>> allItems;

    public BucketListItemViewModel(@NonNull Application application) {
        super(application);
        repository = new BucketListItemRepository(application);
        allItems = repository.getAllItems();
    }

    public void insert(BucketListItem item){
        repository.insert(item);
    }
    public void update(BucketListItem item){
        repository.update(item);
    }
    public LiveData<List<BucketListItem>> getAllItems() {
        return allItems;
    }
}
