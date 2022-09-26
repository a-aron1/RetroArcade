package com.wordle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.*;

public class WordleTest {

    @BeforeClass
    public void start() {
        System.out.println("Begin tests");
    }

    @AfterClass
    public void end() {
        System.out.println("End tests");
    }
}