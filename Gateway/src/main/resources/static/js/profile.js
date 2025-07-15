document.addEventListener("DOMContentLoaded", () => {
    const nickName = localStorage.getItem("nickName");
    document.getElementById("nickname").value = nickName || "";
});

function showSection(type) {
    const edit = document.getElementById("edit-section");
    const del = document.getElementById("delete-section");

    if (type === 'edit') {
        edit.classList.remove("hidden");
        del.classList.add("hidden");
    } else {
        edit.classList.add("hidden");
        del.classList.remove("hidden");
    }
}

document.getElementById("profile-form").addEventListener("submit", async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("userToken");
    const newNick = document.getElementById("nickname").value;
    const currentPw = document.getElementById("current-password").value;
    const newPw = document.getElementById("new-password").value;

    try {
        await axios.put("/api/account/update", {
            currentPassword: currentPw,
            newPassword: newPw,
            newNickName: newNick
        }, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        alert("정보 수정 완료!");
        localStorage.setItem("nickName", newNick);
        window.location.href = "/pages/dashboard.html";

    } catch (err) {
        alert("수정 실패: " + (err.response?.data?.message || err.message));
    }
});

document.getElementById("delete-form").addEventListener("submit", async (e) => {
    e.preventDefault();
    const pw = document.getElementById("delete-password").value;
    const token = localStorage.getItem("userToken");

    if (!confirm("정말 탈퇴하시겠습니까?")) return;

    try {
        await axios.delete("/api/account/delete", {
            headers: {
                Authorization: `Bearer ${token}`
            },
            data: { password: pw }
        });

        alert("탈퇴가 완료되었습니다.");
        localStorage.clear();
        window.location.href = "/login.html";

    } catch (err) {
        alert("탈퇴 실패: " + (err.response?.data?.message || err.message));
    }
});

function goToDashboard() {
    window.location.href = "/pages/dashboard.html";
}

function logout() {
    localStorage.clear();
    window.location.href = "/login.html";
}