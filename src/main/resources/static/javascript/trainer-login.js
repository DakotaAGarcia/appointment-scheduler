document.addEventListener("DOMContentLoaded", () => {

    const hamburgerIcon = document.querySelector('.hamburger-icon');
    const dropdownMenu = document.querySelector('.dropdown-menu');

    hamburgerIcon.addEventListener('click', () => {
        dropdownMenu.classList.toggle('show-dropdown-menu');
    });
    let loginForm = document.getElementById('login-form');
    let loginUsername = document.getElementById('login-username');
    let loginPassword = document.getElementById('login-password');

    const headers = {
        'Content-Type': 'application/json'
    };

    const baseUrl = 'http://localhost:8080/trainers';

    const handleSubmit = async (e) => {
        e.preventDefault();

        let bodyObj = {
            username: loginUsername.value,
            password: loginPassword.value
        };

        const response = await fetch(`${baseUrl}/login`, {
            method: "POST",
            body: JSON.stringify(bodyObj),
            headers: headers
        })
        .catch(err => console.error(err.message));

        const responseArr = await response.json();
        console.log('Response Array:', responseArr);


if (response.status === 200) {
    document.cookie = `trainerId=${responseArr[1]}`;
      console.log('Cookie Set:', document.cookie);
      console.log('All Cookies:', document.cookie.split(";"));
    window.location.replace(responseArr[0]);
}
    };

    loginForm.addEventListener("submit", handleSubmit);
});
