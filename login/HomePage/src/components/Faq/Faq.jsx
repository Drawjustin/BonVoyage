import React, { useState } from "react";
import styles from './Faq.module.scss';
import 'bootstrap/dist/css/bootstrap.min.css';
import Accordion from 'react-bootstrap/Accordion';

const Faq = () => {

  return (
    <div className={styles.plz}>
    <h3 className={styles.title}>FAQ</h3>
    <Accordion defaultActiveKey="0">
      <Accordion.Item eventKey="0">
        <Accordion.Header>Q. 경매는 어떻게 진행 되나요?</Accordion.Header>
        <Accordion.Body className={styles.body}>
        A.  사전에 입찰 신청을 하고 나면 경매 링크에 접속 후 경매 진행과정에 대한 안내 영상이 나온 후 경매 진행 시간이 지난 후 최종 낙찰된 사람은 작가와의 1:1 세션으로 넘어간 후 간이 계약서 작성 후 거래를 성사시킵니다.
        </Accordion.Body>
      </Accordion.Item>

      <Accordion.Item eventKey="1">
        <Accordion.Header>Q. 작품의 수량은 얼마인가요?</Accordion.Header>
        <Accordion.Body className={styles.body}>
        A.  경매 작품과 일반 판매 작품은 모두 각 작품의 수량은 1개 입니다.
        </Accordion.Body>
      </Accordion.Item>
      
      <Accordion.Item eventKey="2">
        <Accordion.Header>Q. 작가 홈페이지는 어떤 걸 하는 곳인가요?</Accordion.Header>
        <Accordion.Body className={styles.body}>
        A.  작가 홈페이지는 작가와 팬의 소통 공간으로 작가의 이력, 작품 및 포트폴리오, 경매 일정 등을 확인할 수 있습니다. 
        </Accordion.Body>
      </Accordion.Item>

      <Accordion.Item eventKey="3">
        <Accordion.Header>Q. 결제 방식/포인트는(은) 무엇인가요?</Accordion.Header>
        <Accordion.Body className={styles.body}>
        A.  모든 결제 방식은 포인트를 통해서 이루어지기 때문에 우선 포인트를 충전한 후에 충전한 포인트로 작품을 구매하는 절차입니다.
        </Accordion.Body>
      </Accordion.Item>
    </Accordion>
    </div>
  );
}

export default Faq
