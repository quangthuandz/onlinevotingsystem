<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Positional Ranking List</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                const sortableList = document.querySelector(".sortable-list");
                const items = sortableList.querySelectorAll(".item");
                items.forEach(item => {
                    item.addEventListener("dragstart", () => {
                        // Adding dragging class to item after a delay
                        setTimeout(() => item.classList.add("dragging"), 0);
                    });
                    // Removing dragging class from item on dragend event
                    item.addEventListener("dragend", () => item.classList.remove("dragging"));
                });
                const initSortableList = (e) => {
                    e.preventDefault();
                    const draggingItem = document.querySelector(".dragging");
                    // Getting all items except currently dragging and making array of them
                    let siblings = [...sortableList.querySelectorAll(".item:not(.dragging)")];
                    // Finding the sibling after which the dragging item should be placed
                    let nextSibling = siblings.find(sibling => {
                        return e.clientY <= sibling.offsetTop + sibling.offsetHeight / 2;
                    });
                    // Inserting the dragging item before the found sibling
                    sortableList.insertBefore(draggingItem, nextSibling);
                };
                sortableList.addEventListener("dragover", initSortableList);
                sortableList.addEventListener("dragenter", e => e.preventDefault());
            });
        </script>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap');
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Poppins', sans-serif;
            }
            body {
                display: flex;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
                background: #595DB8;
            }

            .container {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
            }
            .sortable-listform {
                width: 425px;
                background: #fff;
                border-radius: 7px;
            }
            .form-button{
                list-style: none;
                display: flex;
                align-items: center;
                justify-content: center;
                margin:10px auto;
            }
            .sortable-list {
                width: 425px;
                padding: 25px;
                background: #fff;
                border-radius: 7px;
                padding: 30px 25px 20px;
            }
            .sortable-list .item {
                list-style: none;
                display: flex;
                cursor: move;
                background: #fff;
                align-items: center;
                border-radius: 5px;
                padding: 10px 13px;
                margin-bottom: 11px;
                /* box-shadow: 0 2px 4px rgba(0,0,0,0.06); */
                border: 1px solid #ccc;
                justify-content: space-between;
            }
            .item .details {
                display: flex;
                align-items: center;
            }
            .item .details img {
                height: 43px;
                width: 43px;
                pointer-events: none;
                margin-right: 12px;
                object-fit: cover;
                border-radius: 50%;
            }
            .item .details span {
                font-size: 1.13rem;
            }
            .item i {
                color: #474747;
                font-size: 1.13rem;
            }
            .item.dragging {
                opacity: 0.6;
            }
            .item.dragging :where(details, i) {
                opacity: 0;
            }
            *
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Positional Ranking List</h1>
            <form action="editVote" method="post">
                <input type="hidden" name="Cid" value="${c.getId()}">
                <input type="hidden" name="ballotSize" value="${candidates.size()}">
                <div class="sortable-listform">
                    <ul class="sortable-list" style="max-height: 400px;overflow-y: auto">
                        <c:forEach var="candidate" items="${candidates}" varStatus="loop">
                            <li name="rank${loop.index}" class="item" draggable="true" value="${loop.index}">
                                <input type="hidden" name="score${loop.index}" value="${candidates.size() - loop.index}" class="Score">
                                <input type="hidden" name="id${loop.index}" value="${candidate.getVotingObjectId()}" class="CandidateID">
                                <div class="details">
                                    <img src="${candidate.getImgPath()}">
                                    <span>${candidate.getVotingObjectName()}</span>
                                </div>
                                <i class="uil uil-draggabledots"></i>
                                <p>${candidate.getVotingObjectDescription()}</p>
                                <select class="select-position">
                                    <c:forEach var="position" begin="1" end="${candidates.size()}">
                                        <option value="${position}" ${position == loop.index+1 ?'selected':''}>${position}</option>
                                    </c:forEach>
                                </select>
                            </li>
                        </c:forEach>
                    </ul>
                    <ul class="form-button">
                        <li style="margin-right: 10px; margin-bottom: 2px">
                            <a role="button" class="btn btn-primary" href="ViewCampaignDetails?id=${c.getId()}">Back To Campaign</a>
                        </li>
                        <li style="margin-bottom: 2px">
                            <button id="saveButton" class="btn btn-primary">Save</button>
                        </li>
                    </ul>
                </div>
        </div>
    </form>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const sortableList = document.querySelector(".sortable-list");

        function setupItemEventListeners(item) {
            item.addEventListener("dragstart", () => {
                setTimeout(() => item.classList.add("dragging"), 0);
            });

            item.addEventListener("dragend", () => {
                item.classList.remove("dragging");
                updateDragAndDrop();
            });

            const selectPosition = item.querySelector(".select-position");
            selectPosition.addEventListener("change", () => {
                updateSelectRank();
            });
        }

        function updateDragAndDrop() {
            const rankedItems = sortableList.querySelectorAll(".item");
            const listSize = rankedItems.length;
            rankedItems.forEach((item, index) => {
                const liName = "rank" + index;
                const CandidateId = "id" + index;
                const score = "score" + index;
                const value = listSize - index;
                item.setAttribute("name", liName);
                item.querySelector(".CandidateID").setAttribute("name", CandidateId);
                item.querySelector(".Score").setAttribute("value", value);
                item.querySelector(".Score").setAttribute("name", score)
                item.querySelector(".select-position").value = index + 1;
            });
        }

        function updateSelectRank() {
            const rankedItems = sortableList.querySelectorAll(".item");
            const listSize = rankedItems.length;

            // Sort the items based on their selected position in the dropdown
            const sortedItems = Array.from(rankedItems).sort((a, b) => {
                const aPosition = parseInt(a.querySelector(".select-position").value);
                const bPosition = parseInt(b.querySelector(".select-position").value) + 1;
                return aPosition - bPosition;
            });

            // Create a temporary document fragment to hold the sorted items
            const tempFragment = document.createDocumentFragment();

            // Update the ranks for each item based on their relative order
            sortedItems.forEach((item, index) => {
                const liName = "rank" + index;
                const inputName = "id" + index;
                const score = "score" + index;
                const value = listSize - index;
                item.setAttribute("name", liName);
                item.querySelector(".CandidateID").setAttribute("name", inputName);
                item.querySelector(".Score").setAttribute("value", value);
                item.querySelector(".Score").setAttribute("name", score);

                // Get the current position of the item from its index in the sorted list
                const currentPosition = index + 1;

                // Update the options of the select dropdown
                const selectPosition = item.querySelector(".select-position");
                selectPosition.innerHTML = ""; // Clear previous options
                for (let position = 1; position <= listSize; position++) {
                    const option = document.createElement("option");
                    option.value = position;
                    option.textContent = position;
                    if (position === currentPosition) {
                        option.selected = true; // Select the current position in the dropdown
                    }
                    selectPosition.appendChild(option);
                }

                // Append the sorted item to the temporary fragment
                tempFragment.appendChild(item);
            });

            // Clear the current list
            sortableList.innerHTML = "";

            // Append the sorted items back to the list in their updated order
            sortableList.appendChild(tempFragment);

            // Re-attach event listeners to the updated items
            sortedItems.forEach(item => {
                setupItemEventListeners(item);
            });
        }

        // Set up the initial event listeners
        const items = sortableList.querySelectorAll(".item");
        items.forEach(item => {
            setupItemEventListeners(item);
        });
    });
</script>
</body>
</html>