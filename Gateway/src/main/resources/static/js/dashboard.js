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

// ✅ 국내여행지 검색 기능 (백엔드 연동)
async function searchDomestic() {
    const title = document.getElementById("domestic-title").value.trim();
    const category = document.getElementById("domestic-category").value.trim();
    const location = document.getElementById("domestic-location").value.trim();
    const resultBox = document.getElementById("domestic-result");

    // // ✅ 입력값 확인
    // if (!title || !category || !location) {
    //     resultBox.textContent = "모든 값을 입력해주세요.";
    //     return;
    // }

    try {
        const token = localStorage.getItem("userToken"); // 토큰 가져오기

        const response = await axios.post("/api/place/search", {
            title,
            category,
            location
        }, {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        const data = response.data;

        // ✅ 결과 출력 (리스트 형태 가정)
        if (Array.isArray(data) && data.length > 0) {
            const listHtml = data.map(place =>
                `<li>📍 ${place.title} (${place.location} - ${place.category})</li>`
            ).join("");

            resultBox.innerHTML = `<ul>${listHtml}</ul>`;
        } else {
            resultBox.textContent = "검색 결과가 없습니다.";
        }

    } catch (error) {
        console.error("검색 실패:", error);
        resultBox.textContent = "검색 중 오류가 발생했습니다.";
    }
}
// ✅ 랜덤여행지 추천 기능
async function searchRandom() {
    const title = document.getElementById("random-title").value.trim();
    const category = document.getElementById("random-category").value.trim();
    const location = document.getElementById("random-location").value.trim();
    const resultBox = document.getElementById("random-result");

    try {
        const token = localStorage.getItem("userToken"); // 토큰 가져오기

        const response = await axios.post("/api/place/search", {
            title,
            category,
            location
        }, {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        const data = response.data;

        // ✅ 결과 출력 (랜덤 하나만 선택)
        if (Array.isArray(data) && data.length > 0) {
            const randomIndex = Math.floor(Math.random() * data.length);
            const place = data[randomIndex];

            resultBox.innerHTML = `
                <div>
                    <h3>✨ 랜덤 추천 장소</h3>
                    <p>📍 <strong>${place.title}</strong></p>
                    <p>📍 위치: ${place.location}</p>
                    <p>📁 카테고리: ${place.category}</p>
                </div>
            `;
        } else {
            resultBox.textContent = "추천 결과가 없습니다.";
        }

    } catch (error) {
        console.error("검색 실패:", error);
        resultBox.textContent = "추천 중 오류가 발생했습니다.";
    }
}
// 🔁 왼쪽 버튼 클릭 시 해당 입력 폼 보여주기
function showPlaceView(action) {
    // 전체 폼 숨기기
    document.querySelectorAll(".place-form").forEach(form => form.style.display = "none");

    // 선택된 폼만 보여주기
    const targetId = `place-form-${action}`;
    const targetForm = document.getElementById(targetId);
    if (targetForm) {
        targetForm.style.display = "block";
    }
}