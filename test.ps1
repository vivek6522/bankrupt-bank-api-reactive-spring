
curl --no-buffer --header "Content-Type: application/json" --request POST --data '{\"accountNumber\":\"NL12BNKR0123456789\",\"accountType\":\"CURRENT\",\"balance\":"123456",\"customerId\":\"1\",\"preferred\":true}' http://localhost:8080/accounts
curl --no-buffer --header "Content-Type: application/json" --request POST --data '{\"accountNumber\":\"NL34BNKR0123456789\",\"accountType\":\"CURRENT\",\"balance\":"123456",\"customerId\":\"2\",\"preferred\":true}' http://localhost:8080/accounts

curl --no-buffer http://localhost:8080/accounts/1
curl --no-buffer http://localhost:8080/accounts/2
curl --no-buffer http://localhost:8080/accounts/customers/1
curl --no-buffer http://localhost:8080/accounts/customers/2

curl --no-buffer --header "Content-Type: application/json" --request POST --data '{\"source\":1,\"target\":2,\"amount\":"1",\"description\":\"test\"}' http://localhost:8080/transactions
curl --no-buffer --header "Content-Type: application/json" --request POST --data '{\"source\":2,\"target\":1,\"amount\":"1",\"description\":\"test\"}' http://localhost:8080/transactions

curl --no-buffer http://localhost:8080/transactions/1
curl --no-buffer http://localhost:8080/transactions/2
curl --no-buffer http://localhost:8080/transactions/source/1
curl --no-buffer http://localhost:8080/transactions/source/2
curl --no-buffer http://localhost:8080/transactions/target/1
curl --no-buffer http://localhost:8080/transactions/target/2
