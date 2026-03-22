// Fetch the file system tree from the backend and render it
const apiUrl = 'http://localhost:8080/api/filesystem/tree';

async function fetchFileTree(path = '/') {
    try {
        const response = await fetch(`${apiUrl}?path=${encodeURIComponent(path)}`);
        const data = await response.json();
        console.log('File Tree Data:', data);  // Debugging log
        return data;
    } catch (error) {
        console.error('Error fetching file tree:', error);
        return [];
    }
}

function createTreeNode(node) {
    const li = document.createElement('li');
    li.className = node.type;

    const span = document.createElement('span');
    span.textContent = node.name;
    span.onclick = () => toggleFolder(span);

    li.appendChild(span);

    if (node.type === 'directory') {
        const ul = document.createElement('ul');
        ul.className = 'file-tree';
        li.appendChild(ul);
        li.onclick = (e) => {
            e.stopPropagation(); // Prevent propagation to span click handler
            fetchAndRenderTree(node.path, ul);
        };
    }

    return li;
}

async function fetchAndRenderTree(path, parentUl) {
    const nodes = await fetchFileTree(path);
    if (nodes.length === 0) {
        parentUl.innerHTML = '<li>No files or directories available.</li>';
    } else {
        parentUl.innerHTML = ''; // Clear existing content

        nodes.forEach(node => {
            const treeNode = createTreeNode(node);
            parentUl.appendChild(treeNode);
        });
    }
}

function toggleFolder(span) {
    const li = span.parentElement;
    li.classList.toggle('open');
}

async function initializeFileExplorer() {
    const rootUl = document.getElementById('fileTree');
    await fetchAndRenderTree('/', rootUl);  // Start at the root directory
}

initializeFileExplorer();
