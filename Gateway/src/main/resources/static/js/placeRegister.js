// ✅ 공통: 토큰 가져오기
function getAuthHeader() {
    const token = localStorage.getItem("userToken");
    return { Authorization: `Bearer ${token}` };
}

// 📌 1. 전체 여행지 조회
async function getAllPlaces() {
    try {
        const res = await axios.get("/place/list");
        const box = document.getElementById("place-list-box");
        box.innerHTML = res.data.map(p => `
            <div class="result-box">
                <strong>${p.title}</strong><br>
                ${p.category} | ${p.location}<br>
                📍 ${p.address}<br>
                📝 ${p.content}
            </div>`).join("<hr>");
    } catch (err) {
        alert("조회 실패: " + (err.response?.data?.message || err.message));
    }
}

// 📌 2. place_id로 조회
async function getPlaceById() {
    const id = document.getElementById("place-id").value;
    try {
        const res = await axios.get(`/place/${id}`);
        document.getElementById("place-result").innerHTML = `
            <div class="result-box">
                <strong>${res.data.title}</strong><br>
                ${res.data.category} | ${res.data.location}<br>
                📍 ${res.data.address}<br>
                📝 ${res.data.content}
            </div>`;
    } catch (err) {
        alert("조회 실패: " + (err.response?.data?.message || err.message));
    }
}

// 📌 3. 조건 검색
async function searchPlace() {
    const body = {
        title: document.getElementById("title").value,
        category: document.getElementById("category").value,
        location: document.getElementById("location").value,
    };
    try {
        const res = await axios.post("/place/search", body);
        const out = res.data.map(p => `
            <div class="result-box">
                <strong>${p.title}</strong><br>
                ${p.category} | ${p.location}<br>
                📍 ${p.address}<br>
                📝 ${p.content}
            </div>`).join("<hr>");
        document.getElementById("search-result").innerHTML = out;
    } catch (err) {
        alert("검색 실패: " + (err.response?.data?.message || err.message));
    }
}

// 📌 4. 여행지 수정
async function updatePlace() {
    const body = {
        place_id: document.getElementById("place-id").value,
        title: document.getElementById("title").value,
        content: document.getElementById("content").value,
        category: document.getElementById("category").value,
        location: document.getElementById("location").value,
        address: document.getElementById("address").value,
    };
    try {
        const res = await axios.post("/place/update", body, {
            headers: getAuthHeader()
        });
        alert("수정 완료: " + res.data.title);
    } catch (err) {
        alert("수정 실패: " + (err.response?.data?.message || err.message));
    }
}

// 📌 5. 여행지 삭제
async function deletePlace() {
    const id = document.getElementById("place-id").value;
    try {
        const res = await axios.delete(`/place/delete/${id}`, {
            headers: getAuthHeader()
        });
        alert(res.data.message);
    } catch (err) {
        alert("삭제 실패: " + (err.response?.data?.message || err.message));
    }
}

async function registerPlace() {
    const token = localStorage.getItem("userToken");
    const body = {
        title: document.getElementById("title").value,
        content: document.getElementById("content").value,
        category: document.getElementById("category").value,
        location: document.getElementById("location").value,
        address: document.getElementById("address").value,
    };

    try {
        const response = await axios.post("/place/register", body, {
            headers: { Authorization: `Bearer ${token}` }
        });
        alert(response.data.message);
    } catch (err) {
        alert("등록 실패: " + (err.response?.data?.message || err.message));
    }
}