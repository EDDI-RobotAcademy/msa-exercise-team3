// 대시보드 페이지가 로드될 때 실행
// window.onload = () => {
//     const token = localStorage.getItem("userToken");
//     if (!token) {
//         alert("로그인이 필요합니다.");
//         window.location.href = "/index.html";
//     }
// }
window.addEventListener("DOMContentLoaded", () => {
    const nickName = localStorage.getItem("nickName");

    // "환영합니다, 사용자님!" → "환영합니다, 홍길동님!" 형태로 출력
    const nickSpan = document.getElementById("nick-span");
    if (nickSpan) {
        nickSpan.textContent = nickName || "사용자";
    }
});
// 회원정보 수정 페이지로 이동
function goToProfile() {
    window.location.href = "/pages/profile.html";
}

function logout() {
    alert("로그아웃되었습니다.");
    window.location.href = "/index.html";
}

function searchByKeyword() {
    const keyword = document.getElementById("keyword").value;
    const resultBox = document.getElementById("recommend-result");

    if (!keyword.trim()) {
        resultBox.textContent = "키워드를 입력해주세요!";
        return;
    }

    // 예시 응답
    resultBox.textContent = `🔎 '${keyword}' 관련 추천지를 찾는 중입니다...`;

    setTimeout(() => {
        resultBox.textContent = `✅ 추천 결과: ${keyword}에 어울리는 여행지로 제주도, 여수, 통영이 있습니다!`;
    }, 1000);
}
// 여행지 CRUD 관리 페이지로 이동
function goToPlaceManager() {
    window.location.href = "/pages/place.html";
}
