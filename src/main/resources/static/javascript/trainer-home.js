//Cookie
function getCookie(name) {
    const value = "; " + document.cookie;
    const parts = value.split("; " + name + "=");
    if (parts.length === 2) return parts.pop().split(";").shift();
}
console.log("Cookies:", document.cookie);
const trainerId = getCookie("trainerId");
console.log("Trainer ID:", trainerId);
//DOM Elements
const appointmentContainer = document.getElementById("appointment-container");

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080"

async function getAppointment(trainerId) {
    await fetch(`${baseUrl}/appointments/trainer/${trainerId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => {
            console.log("Fetched appointments:", data);
            createAppointmentCards(data);
        })
        .catch(err => console.error(err))
}
async function getTrainer(trainerId) {
    const response = await fetch(`${baseUrl}/trainers/${trainerId}`, {
        method: "GET",
        headers: headers
    })
        .catch(err => console.error(err));

    if (response.status === 200) {
        return await response.json();
    }
    return null;
}
async function displayGreeting() {
    const trainer = await getTrainer(trainerId);
    if (trainer) {
        const greetingElement = document.getElementById("greeting");
        greetingElement.textContent = `Hello, ${trainer.username}!`;
    }
}

const createAppointmentCards = async (array) => {
    appointmentContainer.innerHTML = '';
    for (const obj of array) {
        let appointmentCard = document.createElement("div");
        appointmentCard.classList.add("m-2");

        appointmentCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.description}</p>
                    <p>Date: ${obj.date}</p>
                    <p>Time: ${obj.time}</p>
                    <p>User: ${obj.username}</p>
                </div>
            </div>
        `;

        appointmentContainer.append(appointmentCard);
    }
};


function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

getAppointment(trainerId);
displayGreeting();
