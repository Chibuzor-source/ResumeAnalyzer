import sys
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

if len(sys.argv) < 3:
    print("Usage: python similarity.py <resume_text> <job_text>")
    sys.exit(1)

resume_Text = sys.agrv[1]
job_Text = sys.agrv[1]

docs = [resume_Text, job_Text]

vectorizer = TfidfVectorizer().fit_transform(docs)
similarity = cosine_similarity(vectorizer[0:1], vectorizer[1:2])[0][0] * 100

print(f"Python TF-IDF Similarity: {similarity:.2f}%")