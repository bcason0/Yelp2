package io.yelp.controller;

import com.beust.jcommander.internal.Lists;
import io.yelp.domain.Business;
import io.yelp.repo.BusinessRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;


import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BusinessCtrlSpec {

    @Mock
    private BusinessRepo businessRepo;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBusinesses(){
        BusinessCtrl businessCtrl = new BusinessCtrl();
        ReflectionTestUtils.setField(businessCtrl, "businessRepo", businessRepo);

        when(businessRepo.findAll()).thenReturn(new ArrayList<Business>());
        ResponseEntity<Iterable<Business>> allBusinessEntity = businessCtrl.getBusiness();

        System.out.println("***********" + Lists.newArrayList(allBusinessEntity.getBody()).size());

        verify(businessRepo, times(1)).findAll();
        assertEquals(HttpStatus.OK, allBusinessEntity.getStatusCode());
        assertEquals(1, Lists.newArrayList(allBusinessEntity.getBody()).size());
    }

}
