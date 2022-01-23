  auto t0 = chrono::steady_clock::now();
  auto diff = chrono::steady_clock::now() - t0;
  cout << chrono::duration<double, milli>(diff).count() << " ms" << endl;
