document.addEventListener('DOMContentLoaded', function() {
	let correctCount = 0;
	const problemCards = document.querySelectorAll(".problem-card");
	problemCards.forEach((card) => {
		const choices = card.querySelectorAll(".choices li");
		const result = card.querySelector('.result');
		const annotation = card.querySelector(".annotation");

		choices.forEach((choice) => {
			choice.addEventListener("click", (event) => {
				if (event.target.classList.contains("correct")) {
					correctCount++;
					result.textContent = "正解です！";
					result.style.backgroundColor = "#c9ecc6"; // 目に優しい色
				} else {
					result.textContent = "不正解です";
					result.style.backgroundColor = "#f4b0ad";
				}
				annotation.style.display = "block";
			});
			
			choices.forEach((choice) => {
				choice.removeEventListener("click", event);
			});
		});
	});

	//	const choices = document.querySelectorAll('.choice');
	//	
	//	
	//	choices.forEach(choice => {
	//		choice.addEventListener('click', function() {
	//			const selectedAnswer = this.textContent.trim().substring(3); // 選択肢の文字列から番号を除外
	//			const problem = this.closest('.problem');
	//			const correctChoice = problem.querySelector('.correct').textContent.trim().substring(3); // 正解の選択肢から番号を除外
	//			const result = problem.querySelector('.result');
	//			const explanation = problem.querySelector('.explanation');
	//
	//			result.textContent = selectedAnswer === correctChoice ? '正解です！' : '不正解です';
	//			explanation.style.display = 'block';
	//
	//			// 正解不正解に応じてカードの背景色を設定
	//			problem.style.backgroundColor = selectedAnswer === correctChoice ? 'lightgreen' : 'lightcoral';
	//			
	//			if (selectedAnswer === correctChoice) {
	//				correctCount++;
	//				document.getElementById("score").textContent = correctCount;
	//			} 
	//		});
	//	});
});