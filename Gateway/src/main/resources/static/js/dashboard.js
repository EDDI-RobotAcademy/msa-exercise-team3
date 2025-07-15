// ✅ 닉네임 출력
window.addEventListener("DOMContentLoaded", () => {
    const nickName = localStorage.getItem("nickName");
    document.getElementById("nick-span").textContent = nickName || "사용자";

    // 기본 탭 열기
    showTab("domestic");
});

// ✅ 로그아웃 처리
function logout() {
    localStorage.clear();
    window.location.href = "/index.html";
}

// ✅ 마이페이지 이동
function goToProfile() {
    window.location.href = "/pages/profile.html";
}

// ✅ 탭 전환 기능
function showTab(tabName) {
    const tabs = ["domestic", "random", "manage", "review"];
    tabs.forEach(name => {
        const section = document.getElementById(`tab-${name}`) || document.getElementById(`tab-${name}-container`);
        if (section) {
            section.classList.add("hidden");
        }
    });


    const target = document.getElementById(`tab-${tabName}`);
    if (target) {
        target.classList.remove("hidden");
    }

}

// ✅ 국내여행지 검색 기능
function searchDomestic() {
    const title = document.getElementById("domestic-title").value.trim();
    const category = document.getElementById("domestic-category").value.trim();
    const location = document.getElementById("domestic-location").value.trim();
    const resultBox = document.getElementById("domestic-result");

    // if (!title || !category || !location) {
    //     resultBox.textContent = "모든 값을 입력해주세요.";
    //     return;
    // }

    // ✨ 가상의 결과 출력
    resultBox.textContent =
        `📌 [${location}]에서 '${category}' 유형의 장소 "${title}"에 대한 결과를 검색했습니다.`;
}

// ✅ 랜덤여행지 추천 기능
function searchRandom() {
    const title = document.getElementById("random-title").value.trim();
    const category = document.getElementById("random-category").value.trim();
    const location = document.getElementById("random-location").value.trim();
    const resultBox = document.getElementById("random-result");

    // if (!category || !location) {
    //     resultBox.textContent = "카테고리와 지역을 모두 입력해주세요.";
    //     return;
    // }

    // ✨ 가상의 랜덤 결과 출력
    const samplePlaces = ["제주도", "부산 해운대", "여수 낭만포차", "강릉 안목해변", "속초 중앙시장"];
    const randomPlace = samplePlaces[Math.floor(Math.random() * samplePlaces.length)];

    resultBox.textContent =
        `🎲 [${location}] 지역에서 '${category}' 테마로 추천된 여행지: ${randomPlace}`;
}
