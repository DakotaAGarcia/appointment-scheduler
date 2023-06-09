document.addEventListener("DOMContentLoaded", () => {

    const hamburgerIcon = document.querySelector('.hamburger-icon');
    const dropdownMenu = document.querySelector('.dropdown-menu');

    hamburgerIcon.addEventListener('click', () => {
        dropdownMenu.classList.toggle('show-dropdown-menu');
    });
    const registerForm = document.getElementById('register-form')
    const registerEmail = document.getElementById('register-email')
    const registerUsername = document.getElementById('register-username')
    const registerBio = document.getElementById('register-bio')
    const registerPassword = document.getElementById('register-password')

    const headers = {
        'Content-Type': 'application/json'
    }

    const baseUrl = 'http://localhost:8080/trainers'


    const handleSubmit = async (e) => {
        e.preventDefault()

        let bodyObj = {
            email: registerEmail.value,
            username: registerUsername.value,
            bio: registerBio.value,
            password: registerPassword.value
        }

        const response = await fetch(`${baseUrl}/register`, {
            method: "POST",
            body: JSON.stringify(bodyObj),
            headers: headers
        })
            .catch(err => console.error(err.message))

        const responseArr = await response.json()

        if (response.status === 200) {
            window.location.replace(responseArr[0])
        }
    }

    registerForm.addEventListener("submit", handleSubmit)

});