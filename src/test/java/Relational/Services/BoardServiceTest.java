package Relational.Services;

import Relational.DAOs.ConnectionDAO;
import Relational.Models.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class BoardServiceTest {
  ConnectionDAO dao;
  BoardService service;

  @BeforeEach
  void setUp() {
    dao = new ConnectionDAO();
    dao.clear();
    service = new BoardService();
  }

  @AfterEach
  void tearDown() {
    dao.clear();
  }

  @Test
  void loadDummyDataModerate() throws Exception{
    service.loadDummyDataModerate();
    List<Connection> map = dao.getMap();
    Assertions.assertEquals(360, map.size());
    //^^ I know it's 360, we load 180 connections that are all duplicated
  }
}