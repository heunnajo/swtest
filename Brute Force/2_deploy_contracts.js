const fs = require('fs');
const AdditionGame = artifacts.require('./AdditionGame.sol')

module.exports = function (deployer) {
  deployer.deploy(AdditionGame)
  .then(() => {
      if(AdditionGame._json){
        fs.writeFile('deployedABI',JSON.stringify(AdditionGame._json.abi),
            (err) => {
                if(err) throw err;
                console.log("파일에 ABI 입력 성공");//블록체인과 컨트랙간의 상호작용할 수 있는 내용.
            }
        )
        fs.writeFile('deployedAddress',AdditionGame.address,
            (err) => {
                if(err) throw err;
                console.log("파일에 주소 입력 성공");
            }
        )
      }
  })
}
