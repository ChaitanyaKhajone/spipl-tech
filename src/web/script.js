let allAppointments = [];
fetch("appointments.json")
    .then(response => response.json())
        .then(data => {
            allAppointments = data;
            populateDoctorFilter(data);
            displayAppointments(data);
        })
        .catch(error => {
            console.error("Error loading appointments:", error);
        });
function displayAppointments(data)
{
    const tableBody = document.getElementById("appointmentTableBody");
    tableBody.innerHTML = "";
    let i = 1;
    data.forEach(app => {
        let statusClass = "";
        if (app.status === "BOOKED") {
            statusClass = "status-booked-JSON";
        } else {
            statusClass = "status-cancelled-JSON";
        }
        let row = `
            <tr>
                <td>${i++}</td>
                <td>${app.doctorName}</td>
                <td>${app.patientName}</td>
                <td>${app.patientContact}</td>
                <td>${app.specialisation}</td>
                <td>₹${app.fees}</td>
                <td>${app.time}</td>
                <td>
                    <span class="${statusClass}">
                        ${app.status}
                    </span>
                </td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
}
function populateDoctorFilter(data)
{
    const doctorFilter = document.getElementById("doctorFilter");
    let doctors = [...new Set(data.map(app => app.doctorName))];
    doctors.forEach(doctor => {
        let option = document.createElement("option");
        option.value = doctor;
        option.textContent = doctor;
        doctorFilter.appendChild(option);
    });
}
document.getElementById("doctorFilter")
        .addEventListener("change", applyFilters);
document.getElementById("statusFilter")
        .addEventListener("change", applyFilters);
document.getElementById("searchPatient")
        .addEventListener("keyup", applyFilters);
function applyFilters()
{
    let selectedDoctor =
            document.getElementById("doctorFilter").value;
    let selectedStatus =
            document.getElementById("statusFilter").value;
    let patientSearch =
        document.getElementById("searchPatient")
            .value
            .toLowerCase();
    let filteredData = allAppointments.filter(app => {
        let doctorMatch =
            selectedDoctor === "All" ||
                app.doctorName === selectedDoctor;
        let statusMatch =
            selectedStatus === "All" ||
                app.status === selectedStatus;
        let patientMatch = app.patientName
                .toLowerCase()
                .includes(patientSearch);
        return doctorMatch &&
            statusMatch &&
                patientMatch;
    });
    displayAppointments(filteredData);
}