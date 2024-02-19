export const PRODUCTS_PER_PAGE = 6;

class LocalStorage {
    constructor() {}
  
    static setItem(key: string, value: string) {
      if (typeof window !== 'undefined') {
        localStorage.setItem(key, value);
      }
    }
    
    // getItem 은 return 을 넣어줘야함! setItem, removeItem이랑 다르게 값을 보여주는녀석!
    static getItem(key: string) {
      if (typeof window !== 'undefined') {
        return localStorage.getItem(key);
      }
      // window객체 localStorage, sessionStorage는 값이 없을때 null
      return null; 
    }
  
    static removeItem(key: string) {
      if (typeof window !== 'undefined') {
        localStorage.removeItem(key);
      }
    }
  }
  
  export default LocalStorage;

export const API_HOST = process.env.REACT_APP_API_HOST;
export const FetchStatus = {
  Request: 'Request',
  Success: 'Success',
  Fail: 'Fail',
};

export const ImageList = [
  'https://seoartgallery.com/wp-content/uploads/2016/07/828px-Vincent_Van_Gogh_-_Three_Sunflowers_F453-510x631.jpg',
  'https://i.pinimg.com/736x/97/95/ae/9795ae9009f61d5e1b01d78ec5cae030.jpg',
  'https://www.m-i.kr/news/photo/201703/297262_166384_5948.jpg',
  'https://auctiondaily.com/wp-content/uploads/2022/10/Korea-News-Breeze-Art-Fair-Opens-Where-You-Can-Meet-New-Korean-Artists-1.webp',
  'https://m.pressian.com/_resources/10/2021/09/29/2021092916105639554_l.jpg',
  'https://wimg.mk.co.kr/meet/neds/2022/03/image_readtop_2022_231921_16471547574973113.jpg',
  'https://artrie.com/HyAdmin/naverEditor/upload/1697258735.jpg',
  'https://post-phinf.pstatic.net/MjAyMjA2MjBfMjY4/MDAxNjU1NjkxMDc3NTUz.yoLw0kBWOKguYWyds9cQoQp4IZAXl_wMoxDE4XHGuvcg.eSJw4ShyB8o13YuE-AZ0Lxh42O-rSxtgploWvfO9mCYg.JPEG/20220601_141155.jpg?type=w800_q75',
  'https://image.newdaily.co.kr/site/data/img/2019/03/18/2019031800058_0.jpg',
  'https://og-data.s3.amazonaws.com/media/exhibitions/image/4969/ei_4969.jpg',
  'https://www.yeongnam.com/mnt/file/201808/20180815.010180744080001i1.jpg',
  'https://www.medisobizanews.com/news/photo/202303/105593_95467_1744.jpg',
  'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4EpBShyIrPqYQKr2qz4aUbB57tPVMBUozQBLhHt4S8KpkLnBa9txIVo4FH91hLdvEw1k&usqp=CAU',
  'https://cdn.imweb.me/thumbnail/20221118/5b7fe48c48774.png',
  'https://lh3.googleusercontent.com/proxy/_vt2LnuQz14aX_GXXhd5IB3mRv-WQVzXGqjJaBZJb2n4LhjJ_VpS2oQ5GosF1XuqnoTv40wOhN5FCTyv1mH7ZOv24vwJXDQ3BbcHsnoH',
];

export const ReviewList = [
  'https://image.chosun.com/sitedata/image/201702/20/2017022002398_0.jpg',
  'https://photo.akmall.com/image4/goods/38/18/43/19/138184319_01_350.jpg',
  'https://m.wart.co.kr/file_data/weart22/2022/08/01/b912e893198453807ef53d8c9b8125b4.jpg',
  'https://media.bunjang.co.kr/product/222196100_1_1682414243_w360.jpg',
  'https://contents.lotteon.com/itemimage/20231221131505/LO/20/85/85/14/86/_2/08/58/51/48/7/LO2085851486_2085851487_1.jpg/dims/resizef/720X720',
];