<h1>User form</h1>

<form method="POST" action="/api/users">
  <label for="firstname">Firstname</label>
  <br>
  <input type="text" name="firstname">
  <br>
  <label for="lastname">Lastname</label>
  <br>
  <input type="text" name="lastname">
  <br>
  <label for="email">Email</label>
  <br>
  <input type="email" name="email">
  <br>
  <label for="cellPhone">Cell phone</label>
  <br>
  <input type="text" name="cellPhone">
  <br>
  <label for="homePhone">Home Phone</label>
  <br>
  <input type="text" name="homePhone">
  <br>
  <label for="commentary">Commentary</label>
  <br>
  <textarea name="commentary"></textarea>
  <br>
  <label for="mainContact">Main contact</label>
  <br>
  <input type="checkbox" name="mainContact" value="true">
  <br>
  <label for="address">Address</label>
  <br>
  <input type="number" name="address">
  <br>
   <label for="login">Login</label>
  <br>
  <input type="text" name="login">
  <br>
  <label for="password">Password</label>
  <br>
  <input type="password" name="password">
  <br>
  <label for="role">Role</label>
  <br>
  <select name="role">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  </select>
  <br>

  <br><br>
  <input type="submit" value="Submit">
</form>
