Step 1: Initialize Git (if not already done)
git init

Step 2: Add GitHub Remote
git remote add origin https://github.com/khushikumawat128/Factoryevents.git


If remote already exists, use:

git remote set-url origin https://github.com/khushikumawat128/Factoryevents.git

Step 3: Add All Files
git add .

Step 4: Commit Files
git commit -m "Initial commit"

Step 5: Push to GitHub
git branch -M main
git push -u origin main


Enter your GitHub username and personal access token when asked.

Important Notes

Do not include target/ folder

Ensure README.md is in root folder

If push fails due to authentication, you must use a GitHub token (not password)

Verify

Open:

https://github.com/khushikumawat128/Factoryevents