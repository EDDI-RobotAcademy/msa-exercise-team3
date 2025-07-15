window.addEventListener("DOMContentLoaded", () => {
    const nickName = localStorage.getItem("nickName");
    document.getElementById("nickname").value = nickName || "";
});

document.getElementById("profile-form").addEventListener("submit", async (e) => {
    e.preventDefault();

    const token = localStorage.getItem("userToken");
    const newNick = document.getElementById("nickname").value;
    const newPass = document.getElementById("password").value;

    try {
        const res = await axios.put("/api/account/update", {
            nickName: newNick,
            password: newPass
        }, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        alert("정보가 수정되었습니다.");
        localStorage.setItem("nickName", newNick);
        window.location.href = "/pages/dashboard.html";

    } catch (err) {
        alert("수정 실패: " + (err.response?.data?.message || err.message));
    }
});

function goToDashboard() {
    window.location.href = "/pages/dashboard.html";
}

function logout() {
    localStorage.clear();
    window.location.href = "/login.html";
}
