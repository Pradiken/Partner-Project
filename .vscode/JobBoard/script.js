let posts = [];

const postForm = document.getElementById('postForm');
const postsContainer = document.getElementById('postsContainer');

function loadPosts() {
    const savedPosts = localStorage.getItem('posts');
    if (savedPosts) {
        posts = JSON.parse(savedPosts);
    }
    showPosts();
}

function savePosts() {
    localStorage.setItem('posts', JSON.stringify(posts));
}

postForm.addEventListener('submit', function(e) {
    e.preventDefault();

    const name = document.getElementById('name').value.trim();
    const jobTitle = document.getElementById('jobTitle').value.trim();
    const company = document.getElementById('company').value.trim();
    const workload = document.getElementById('workload').value.trim();
    const workspace = document.getElementById('workspace').value.trim();
    const experience = document.getElementById('experience').value.trim();

    if (!name || !jobTitle || !company || !workload || !workspace || !experience) {
        alert("You cannot leave any answers blank. Please try again.");
        return;
    }

    const newPost = {
        name,
        jobTitle,
        company,
        workload,
        workspace,
        experience,
        comments: []
    };

    posts.push(newPost);
    savePosts();
    showPosts();
    
    postForm.reset();
});

function showPosts() {
    postsContainer.innerHTML = '';

    if (posts.length === 0) {
        postsContainer.innerHTML = '<p class="no-posts">There are no posts yet.</p>';
        return;
    }

    posts.forEach((post, index) => {
        const postCard = document.createElement('div');
        postCard.className = 'post-card';

        let htmlContent = `
            <h3>Post #${index + 1}</h3>
            <p><strong>Name:</strong> ${post.name}</p>
            <p><strong>Job Title:</strong> ${post.jobTitle}</p>
            <p><strong>Company:</strong> ${post.company}</p>
            <p><strong>Workload:</strong> ${post.workload}</p>
            <p><strong>Workspace:</strong> ${post.workspace}</p>
            <p><strong>Experience:</strong> ${post.experience}</p>
            
            <div class="comment-section">
                <strong>Comments:</strong>
                <ul class="comment-list">
        `;

        if (post.comments.length === 0) {
            htmlContent += `<li>No comments yet.</li>`;
        } else {
            post.comments.forEach(comment => {
                htmlContent += `<li>- ${comment}</li>`;
            });
        }

        htmlContent += `
                </ul>
                <div class="comment-input-group">
                    <input type="text" id="comment-input-${index}" placeholder="Write your comment...">
                    <button class="btn btn-sm" onclick="addComment(${index})">Add Comment</button>
                </div>
            </div>
            <button class="btn btn-danger btn-sm" onclick="deletePost(${index})">Delete Post</button>
        `;

        postCard.innerHTML = htmlContent;
        postsContainer.appendChild(postCard);
    });
}

window.addComment = function(index) {
    const inputElement = document.getElementById(`comment-input-${index}`);
    const commentText = inputElement.value.trim();

    if (!commentText) {
        alert("Comment cannot be blank.");
        return;
    }

    posts[index].comments.push(commentText);
    savePosts();
    showPosts();
};

window.deletePost = function(index) {
    if (confirm("Are you sure you want to delete this post?")) {
        posts.splice(index, 1);
        savePosts();
        showPosts();
    }
};

loadPosts();
