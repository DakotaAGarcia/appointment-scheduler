document.addEventListener("DOMContentLoaded", () => {
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("appointment-form")
const appointmentContainer = document.getElementById("appointment-container")

//Modal Elements
let appointmentDescription = document.getElementById('edit-appointment-description')
let updateAppointmentBtn = document.getElementById('update-appointment-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/appointments"

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        date: document.getElementById("appointment-date").value,
        time: document.getElementById("appointment-time").value,
        trainerId: document.getElementById("appointment-trainer").value,
        description: document.getElementById("appointment-input").value
    }
    await addAppointment(bodyObj);
    document.getElementById("appointment-date").value = ''
    document.getElementById("appointment-time").value = ''
    document.getElementById("appointment-trainer").value = ''
    document.getElementById("appointment-input").value = ''
}

async function getTrainers() {
    await fetch("http://localhost:8080/trainers", {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => populateTrainerSelect(data))
        .catch(err => console.error(err));
}

let trainersById = {};

function populateTrainerSelect(trainers) {
    const trainerSelect = document.getElementById("appointment-trainer");
    const editTrainerSelect = document.getElementById("edit-appointment-trainer");

    trainers.forEach(trainer => {
        const option = document.createElement("option");
        option.value = trainer.id;
        option.text = `${trainer.username} (${trainer.email})`;

        trainerSelect.add(option.cloneNode(true));
        editTrainerSelect.add(option);

        trainersById[trainer.id] = trainer;
    });
}




async function addAppointment(obj) {
    console.log(obj);
    const response = await fetch(`${baseUrl}/user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getAppointment(userId);
    }
}

async function getAppointment(userId) {
    await fetch(`${baseUrl}/user/${userId}`, {
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

async function handleDelete(appointmentId){
    await fetch(baseUrl + '/' + appointmentId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getAppointment(userId);
}

async function getAppointmentById(appointmentId) {
    await fetch(baseUrl + '/' + appointmentId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => {
            console.log("Fetched appointment:", data);
            populateModal(data);
        })
        .catch(err => console.error(err.message));
}


async function handleAppointmentEdit(appointmentId) {
    let bodyObj = {
        id: appointmentId,
        date: document.getElementById("edit-appointment-date").value,
        time: document.getElementById("edit-appointment-time").value,
        description: document.getElementById("edit-appointment-description").value
    }

    await fetch(`${baseUrl}/${appointmentId}`, {
              method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getAppointment(userId);
}


const createAppointmentCards = (array) => {
    appointmentContainer.innerHTML = '';
    array.forEach(obj => {
        let appointmentCard = document.createElement("div");
        appointmentCard.classList.add("m-2");

        const trainer = trainersById[obj.trainerId];

        appointmentCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.description}</p>
                    <p>Date: ${obj.date}</p>
                    <p>Time: ${obj.time}</p>
                    <p>Trainer: ${trainer.username} (${trainer.email})</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger delete-btn">Delete</button>
                        <button type="button" class="btn btn-primary edit-btn"
                        data-bs-toggle="modal" data-bs-target="#appointment-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `;

        appointmentCard.querySelector(".delete-btn").addEventListener("click", () => handleDelete(obj.id));
        appointmentCard.querySelector(".edit-btn").addEventListener("click", () => getAppointmentById(obj.id));

        appointmentContainer.append(appointmentCard);
    });
};


function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateModal = (obj) => {
    const trainer = trainersById[obj.trainerId];

    document.getElementById("edit-appointment-date").value = obj.date;
    document.getElementById("edit-appointment-time").value = obj.time;
    document.getElementById("edit-appointment-trainer").value = trainer.id;
    appointmentDescription.value = obj.description;
    updateAppointmentBtn.setAttribute('data-appointment-id', obj.id);
}


getAppointment(userId);

submitForm.addEventListener("submit", handleSubmit)

updateAppointmentBtn.addEventListener("click", (e)=>{
    let appointmentId = e.target.getAttribute('data-appointment-id')
    handleAppointmentEdit(appointmentId);
})
getTrainers();
});