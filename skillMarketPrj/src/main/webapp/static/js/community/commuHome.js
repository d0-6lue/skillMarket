function getSelectedTopic() {
    const topicGroup = document.querySelector(".topic-group");
    const topicLinks = topicGroup.querySelectorAll("a");

    for (const link of topicLinks) {
        if (link.href === window.location.href) {
            return link.textContent;
        }
    }

    return "";
}

// 사이드 바 주제선택하면 글쓰기도 주제 바뀌게하려고 ~
function writePostWithTopic() {
    const root = "${root}";
    const selectedTopic = getSelectedTopic();
    let topicId;

    switch (selectedTopic) {
        case "전체":
            topicId = "all";
            break;
        case "함께해요":
            topicId = "together";
            break;
        case "궁금해요":
            topicId = "qna";
            break;
        case "얼마에요":
            topicId = "how-much";
            break;
        default:
            topicId = "all";
    }

    window.location.href = `post/write?topicid=${topicId}`;
}