package com.gotogether.gotogethersbe.web.api;

public class ResponseMessage {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String CREATED_FAIL = "회원 가입 실패";
    public static final String REISSUE_SUCCESS = "토큰 재발급 성공";
    public static final String CONFIRM_PASSWORD_SUCCESS = "패스워드 확인 성공";
    public static final String CONFIRM_PASSWORD_FAIL = "패스워드 확인 실패";
    public static final String UPDATE_MEMBER_SUCCESS = "회원 비밀번호 수정 성공";
    public static final String CHECK_EMAIL_SUCCESS = "사용할 수 있는 이메일입니다.";
    public static final String CHECK_EMAIL_FAIL = "사용할 수 없는 이메일입니다.";
    public static final String UPDATE_MEMBER_FAIL = "회원 비밀번호 수정 실패";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String RECO_DATA_SUCCESS_FOR_MEMBER = "회원을 위한 추천 데이터 입력 저장 완료";
    public static final String RECO_DATA_SUCCESS_FOR_NONMEMBER = "비회원을 위한 추천 데이터 입력 저장 완료";
    public static final String LOGOUT_SUCCESS = "로그아웃 성공";
    public static final String REQUEST_ERROR = "요청 에러";



    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
    public static final String GET_CUSTOM_BANNER_LIST = " 베너 이미지 조회 성공";
    public static final String GET_PRODUCT_LIST = "상품목록 조회 성공";
    public static final String GET_PRODUCT = "상품조회 성공";
    public static final String GET_CUSTOM_PRODUCT_LIST = "맞춤 상품목록 조회 성공";
    public static final String Get_Continent_Category_List = "여행지 카테고리목록 조회 성공";
    public static final String GET_PRODUCT_CATEGORY = "카태고리별 상품목록 조회 성공";
    public static final String GET_PRODUCT_THEME_GOLF = "골프 테마 상품목록 조회 성공";
    public static final String GET_PRODUCT_CATEGORY_CULTURE = "문화탐방 테마 상품목록 조회 성공";
    public static final String GET_PRODUCT_THEME_HEALING = "휴양지 테마 상품 조회 성공";
    public static final String GET_PRODUCT_SEARCH = "상품검색 성공";

    public static final String ADD_CART = "장바구니 등록 성공";
    public static final String READ_CART = "장바구니 목록 조회 성공";
    public static final String EXIST_CART = "장바구니에 이미 존재";
    public static final String NOT_FOUNT_CART = "장바구니 목록 없음";
    public static final String DELETE_CART = "장바구니 삭제 성공";
    public static final String ORDER_CART = "장바구니 신청 성공";

    public static final String ADD_BOOKMARK = "찜 등록 성공";
    public static final String READ_BOOKMARK = "찜 목록 조회 성공";
    public static final String NOT_FOUND_BOOKMARK = "찜 목록 없음";
    public static final String DELETE_BOOKMARK = "찜 목록 삭제 성공";

    public static final String INSERT_PRODUCT_SUCCESS = "상품 등록 성공";
    public static final String INSERT_PRODUCT_FAIL = "상품 등록 실패";
    public static final String UPDATE_PRODUCT_SUCCESS = "상품 수정 성공";
    public static final String UPDATE_PRODUCT_FAIL = "상품 수정 실패";
    public static final String DELETE_PRODUCT_SUCCESS = "상품 삭제 성공";
    public static final String DELETE_PRODUCT_FAIL = "상품 삭제 실패";
    public static final String GET_PRODUCTS_SUCCESS = "상품 리스트 조회 성공";
    public static final String GET_PRODUCT_SUCCESS = "상품 상세 조회 성공";
    public static final String GET_PRODUCT_FAIL = "상품 상세 조회 실패";
    public static final String NOT_FOUND_PRODUCT = "해당 상품을 찾을 수 없습니다";

    public static final String RESERVATION_SUCCESS = "예약 성공";
    public static final String GET_RESERVATION_LIST = "예약 목록 조회 성공";
    public static final String UPDATE_RESERVATION_STATUS = "예약 상품 상태 수정 성공";
    public static final String DELETE_RESERVATION = "예약 상품 삭제 성공";
    public static final String GET_RESERVATION = "예약 상품 상세 조회 성공";
    public static final String GET_RESERVATION_BY_PERIOD = "최근 예약 상품 기간별 조회 성공";
    public static final String NOT_FOUND_RESERVATION = "해당 예약 상품을 찾을 수 없습니다";

    public static final String GET_RESERVATION_PERSON_LIST = "예약자 목록 조회 성공";
    public static final String ALREADY_EXIST_WISH = "이미 존재하는 찜입니다";
    public static final String WISH_SUCCESS= "찜 성공";
    public static final String GET_WISH_LIST= "찜 목록 조회 성공";
    public static final String DELETE_WISHES= "찜 선택 삭제 성공";
}
