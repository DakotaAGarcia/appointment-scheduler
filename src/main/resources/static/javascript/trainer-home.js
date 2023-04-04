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
        greetingElement.textContent = `Hello, ${trainer.username}! Here is your schedule:`;
    }
}
function editComment(id) {
    const commentElement = document.querySelector(`.appointment-card[data-appointment-id="${id}"] p span.comment`);
    const commentText = commentElement.textContent;

    document.getElementById('commentTextarea').value = commentText;
    document.getElementById('appointmentId').value = id;
    const editCommentModal = new bootstrap.Modal(document.getElementById('editCommentModal'));
    editCommentModal.show();
}

function saveComment() {
    const appointmentId = document.getElementById('appointmentId').value;
    const commentText = document.getElementById('commentTextarea').value;

    // Send the updated comment to the server
    fetch(`${baseUrl}/appointments/${appointmentId}/comment`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            comment: commentText,
        }),
    })
        .then(response => {
            if (response.ok) {
                const commentElement = document.querySelector(`.appointment-card[data-appointment-id="${appointmentId}"] p span.comment`);
                if (commentElement) {
                    commentElement.textContent = commentText;
                }
                const editCommentModalElement = document.getElementById('editCommentModal');
                const editCommentModal = bootstrap.Modal.getInstance(editCommentModalElement);
                editCommentModal.hide();
            } else {
                console.error('Error updating comment:', response.status);
            }
        })
        .catch(error => {
            console.error('Error fetching the update comment API:', error);
        });
}

const createAppointmentCards = async (array) => {
    appointmentContainer.innerHTML = '';
    for (const obj of array) {
        let appointmentCard = document.createElement("div");
        appointmentCard.classList.add("m-2");

        appointmentCard.innerHTML = `
        <div class="card d-flex appointment-card opaque-background" data-appointment-id="${obj.id}">
            <div class="card-body d-flex flex-column justify-content-between appointment-card-body" style="height: available">
                <p class="card-text">${obj.description}</p>
                <p>Date: ${obj.date}</p>
                <p>Time: ${obj.time}</p>
                <p>Rider: ${obj.username}</p>
                <p>Leave a comment: <span class="comment">${obj.comment || 'No comment'}</span></p>
                <button class="btn btn-primary edit-comment-btn" onclick="editComment(${obj.id})">Edit Comment</button>

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
document.addEventListener('DOMContentLoaded', () => {
document.getElementById('saveCommentButton').addEventListener('click', saveComment);
getAppointment(trainerId);
displayGreeting();
});
