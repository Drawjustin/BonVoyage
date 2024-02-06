const express = require('express');
const app = express();
const axios = require('axios');
const bodyParser = require('body-parser');

app.use(bodyParser.json());

app.post('/charge/kakao', async (req, res) => {
  const paymentInfo = req.body;

  // 카카오페이 API 호출을 위한 URL과 토큰 등을 설정
  const kakaoPayApiUrl = 'https://kapi.kakao.com/v1/payment/ready';
  const kakaoPayApiKey = 'DEV416EA664328B176D54F7419E84961D36FD36C';

  try {
    const response = await axios.post(kakaoPayApiUrl, paymentInfo, {
      headers: {
        Authorization: `KakaoAK ${kakaoPayApiKey}`,
      },
    });

    res.json(response.data);
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: '카카오페이 결제 요청 실패' });
  }
});

const port = 3001;
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
