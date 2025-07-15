document.addEventListener("DOMContentLoaded", () => {
    const nickName = localStorage.getItem("nickName");
    document.getElementById("nickname").value = nickName || "";
});

function showSection(type) {
    const edit = document.getElementById("edit");
    const del = document.getElementById("delete");

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
        window.location.href = "/index.html";

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
// async function updateProfile() {
//     const token = localStorage.getItem("userToken");
//
//     const body = {
//         nickname: document.getElementById("edit-nickname").value,
//         currentPassword: document.getElementById("edit-current-password").value,
//         newPassword: document.getElementById("edit-new-password").value,
//     };
//
//     try {
//         const res = await axios.post("/api/account/update", body, {
//             headers: {
//                 Authorization: `Bearer ${token}`,
//                 "Content-Type": "application/json"
//             }
//         });
//
//         // ✅ 성공 팝업 띄우기
//         alert(res.data.message);
//
//         // 입력창 초기화
//         document.getElementById("edit-current-password").value = "";
//         document.getElementById("edit-new-password").value = "";
//         document.getElementById("edit-nickname").value = "";
//
//     } catch (err) {
//         // ❌ 실패 시 팝업으로 표시
//         alert("❌ 수정 실패: " + (err.response?.data?.message || err.message));
//     }
// }
// async function deleteAccount() {
//     const token = localStorage.getItem("userToken");
//     const password = document.getElementById("delete-password").value;
//
//     try {
//         const res = await axios.post("/api/account/delete", { password }, {
//             headers: {
//                 Authorization: `Bearer ${token}`,
//                 "Content-Type": "application/json"
//             }
//         });
//
//         alert(res.data.message || "😢 탈퇴가 완료되었습니다.");
//         localStorage.removeItem("userToken");  // 토큰 제거
//         window.location.href = "/";  // 홈으로 리디렉션
//
//     } catch (err) {
//         document.getElementById("delete-result").textContent =
//             "❌ 탈퇴 실패: " + (err.response?.data?.message || err.message);
//     }
// }
document.getElementById("profile-form").addEventListener("submit", async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("userToken");
    const newNick = document.getElementById("edit-nickname").value;
    const currentPw = document.getElementById("edit-current-password").value;
    const newPw = document.getElementById("edit-new-password").value;

    try {
        const res = await axios.post("/api/account/update", {
            nickname: newNick,
            currentPassword: currentPw,
            newPassword: newPw
        }, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        alert(res.data.message || "정보 수정 완료!");
        localStorage.setItem("nickName", newNick);
        document.getElementById("profile-form").reset(); // 폼 초기화

    } catch (err) {
        alert("수정 실패: " + (err.response?.data?.message || err.message));
    }
});
document.getElementById("delete-form").addEventListener("submit", async (e) => {
    e.preventDefault();

    const pw = document.getElementById("delete-password").value;
    const token = localStorage.getItem("userToken");

    if (!confirm("정말 탈퇴하시겠습니까? 😢")) return;

    try {
        const res = await axios.post("/api/account/delete", { password: pw }, {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        alert(res.data.message || "😢 탈퇴가 완료되었습니다.");
        localStorage.clear();
        window.location.href = "/";

    } catch (err) {
        document.getElementById("delete-result").textContent =
            "❌ 탈퇴 실패: " + (err.response?.data?.message || err.message);
    }
});